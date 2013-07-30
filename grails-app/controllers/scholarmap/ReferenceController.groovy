package scholarmap

import org.springframework.dao.DataIntegrityViolationException

class ReferenceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [referenceInstanceList: Reference.list(params), referenceInstanceTotal: Reference.count()]
    }

    def create() {
        [referenceInstance: new Reference(params)]
    }

    def save() {
        def referenceInstance = new Reference(params)
        if (!referenceInstance.save(flush: true)) {
            render(view: "create", model: [referenceInstance: referenceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reference.label', default: 'Reference'), referenceInstance.id])
        redirect(action: "show", id: referenceInstance.id)
    }

    def show(Long id) {
        def referenceInstance = Reference.get(id)
        if (!referenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "list")
            return
        }

        [referenceInstance: referenceInstance]
    }

    def edit(Long id) {
        def referenceInstance = Reference.get(id)
        if (!referenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "list")
            return
        }

        [referenceInstance: referenceInstance]
    }

    def update(Long id, Long version) {
        def referenceInstance = Reference.get(id)
        if (!referenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (referenceInstance.version > version) {
                referenceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'reference.label', default: 'Reference')] as Object[],
                        "Another user has updated this Reference while you were editing")
                render(view: "edit", model: [referenceInstance: referenceInstance])
                return
            }
        }

        referenceInstance.properties = params

        if (!referenceInstance.save(flush: true)) {
            render(view: "edit", model: [referenceInstance: referenceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reference.label', default: 'Reference'), referenceInstance.id])
        redirect(action: "show", id: referenceInstance.id)
    }

    def delete(Long id) {
        def referenceInstance = Reference.get(id)
        if (!referenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "list")
            return
        }

        try {
            referenceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reference.label', default: 'Reference'), id])
            redirect(action: "show", id: id)
        }
    }




}
