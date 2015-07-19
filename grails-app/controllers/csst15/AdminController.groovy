package csst15

import csst15.command.EmailOnlyCommand
import csst15.conf.FieldLockConf
import csst15.conf.FieldMandatoryConf
import csst15.conf.GeneralConf
import csst15.constants.Roles
import csst15.security.Role
import csst15.security.User
import csst15.security.UserRole
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils
import org.springframework.http.HttpStatus

@Slf4j
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class AdminController {
    def userService
    def excelService

    static allowedMethods = [
            deleteUser               : 'GET',
            manipulateReg            : 'POST',
            manipulateFieldLock      : 'POST',
            board                    : 'GET',
            createUser               : 'POST',
            manipulateFieldMand      : 'POST',
            manipulateFieldVisibility: 'POST'
    ]

    static defaultAction = "board"

    def board() {
        def users = UserRole.findAllByRole(Role.findByAuthority(Roles.USER.name)).user
        def entities = Entity.list()
        def references = Reference.list()
        def isRegEnabled = GeneralConf.findById(1).isRegEnabled
        def fieldMandConf = FieldMandatoryConf.list()
        [users: users, isRegEnabled: isRegEnabled, fieldMandConf: fieldMandConf, references: references, entities: entities]
    }

    @Transactional
    def deleteUser(User user) {
        UserRole.findAllByUser(user).collect {
            it.delete(flush: true)
        }
        ReferenceVote.findAllByUser(user).collect {
            it.delete(flush: true)
        }

        UserEntity.findAllByUser(user).collect {
            it.delete(flush: true)
        }

        user.delete(flush: true)
        log.info("Deleted the user with id ${user.id}")
        redirect(action: 'board')
    }

    @Transactional
    def createUser(EmailOnlyCommand emailCommand) {
        if (emailCommand.hasErrors())
            render(view: 'create', model: [userInstace: emailCommand])
        else {
            def user = userService.createUser(params.email)

            if (user?.id) {
                redirect(action: 'board')
            } else {
                log.error("Failed to register new user account")
                render(view: 'signup', model: [userInstance: emailCommand])
            }
        }
    }

    @Transactional
    def manipulateReg() {
        def generalConf = GeneralConf.findById(1)
        generalConf.isRegEnabled = !generalConf.isRegEnabled
        generalConf.save(failOnError: true)
        log.info("User registration ability is set to ${generalConf.isRegEnabled}")
        render status: HttpStatus.OK
    }

    @Transactional
    def manipulateFieldLock() {
        def fieldName = params.fieldName
        def user = User.findById(params.userId)
        def fieldLockConfig = FieldLockConf.findByUser(user)
        if (fieldName && fieldLockConfig) {
            fieldLockConfig."${fieldName}" = !fieldLockConfig."${fieldName}"
            fieldLockConfig.save(failOnError: true)
            log.info("'${fieldName}' field is locked ${fieldLockConfig."${fieldName}"} for the user with id ${user.id}")
            render status: HttpStatus.OK
        } else {
            log.debug("Field lock config not found for '${user.email}' with id ${user.id}.")
            redirect(action: 'editUserProfile', params: [username: user.username])
        }
    }

    @Transactional
    def manipulateFieldMand() {
        if (params.fieldName) {
            def fieldName = params.fieldName
            def fieldMandConfig = FieldMandatoryConf.findByFieldName(fieldName)
            if (fieldName && fieldMandConfig) {
                fieldMandConfig.isMandatory = !fieldMandConfig.isMandatory
                fieldMandConfig.save(failOnError: true)
                log.info("'${fieldName}' field is mandatory ${fieldMandConfig.isMandatory}")
                render status: HttpStatus.OK
            } else {
                log.debug("Field mandatory config not found.")
                redirect(action: 'board')
            }
        } else {
            log.debug("fieldName not specified.")
            redirect(action: 'board')
        }
    }

    def editUserProfile() {
        def user = User.findByUsername(params.username)
        if (user) {
            def fieldsLockConf = FieldLockConf.findByUser(user)
            render(view: 'edit', model: [user: user, lockConf: fieldsLockConf])
        } else {
            redirect(controller: 'admin')
        }
    }

    @Transactional
    def importUser() {
        def fileName = 'xlsFile'
        if (excelService.readExcelData(request, fileName)) {
            redirect(action: 'board')
        }
    }

    def openMergeRefDialog() {
        def idList = StringUtils.split(params.entities, ",")
        def references = []
        flash.references = idList

        idList.each { id ->
            references.push(Reference.findById(id as Long))
        }

        render(template: 'mergeRefPopup', model: [references: references])
    }

    def openMergeDialog() {
        def idList = StringUtils.split(params.entities, ",")
        def entities = []
        flash.entities = idList

        idList.each { id ->
            entities.push(Entity.findById(id as Long))
        }

        render(template: 'mergePopup', model: [entities: entities])
    }

    @Transactional
    def mergeReferences() {
        if (params.checkBox) {
            def mReference = Reference.get(params.checkBox);

            flash.references.each { id ->
                if (id != params.checkBox) {
                    def reference = Reference.get(id)
                    if (reference) {
                        Reference.withNewTransaction {
                            ReferenceVote.findAllByReferenceAndUser(reference).collect {
                                it.reference = mReference
                                it.save(flush: true)
                            }
                        }

                        Reference.withNewTransaction {
                            ReferenceAuthor.findAllByReference(reference).collect {
                                ReferenceAuthor.findByAuthorAndReference(it.author, mReference) ?: ReferenceAuthor.create(it.author, mReference, true)
                                it.delete(flush: true)
                            }
                        }

                        reference.delete(flush: true)
                    }
                }
            }
        }

        redirect(action: 'board')
    }

    @org.springframework.transaction.annotation.Transactional
    def mergeEntities() {
        if (params.checkBox) {
            def mEntity = Entity.get(params.checkBox);

            flash.entities.each { id ->
                if (id != params.checkBox) {
                    def entity = Entity.get(id)
                    if (entity) {
                        Entity.withNewTransaction {
                            ReferenceVote.findAllByEntity(entity).collect {
                                it.entity = mEntity
                                it.save(flush: true)
                            }
                        }

                        Entity.withNewTransaction {
                            UserEntity.findAllByEntity(entity).collect {
                                UserEntity.create(it.user, mEntity, true)
                                it.delete(flush: true)
                            }
                        }

                        Entity.withNewTransaction {
                            entity.delete(flush: true)
                        }
                    }
                }
            }
        }

        redirect(action: 'board')
    }
}
