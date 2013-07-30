package scholarmap

import org.springframework.dao.DataIntegrityViolationException

class TheoryReferenceVoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def saveVotes(params) {

        if (params.referenceId) {
            for (refId in params.referenceId) {
                Reference reference = Reference.get(refId);
                def theoryReferenceVote = new TheoryReferenceVote([user: session.user, reference: reference, theory: session.theory])
                theoryReferenceVote.save(flush: true)
            }
        }
        redirect(action: "create")

    }

    def vote() {

        def references = Reference.list(params);
        session.step = 2
        if (references.isEmpty()) {
            redirect(action: "create")
        } else {
            [referenceInstanceList: references]
        }
    }

    def addAnother() {
        if (params.year && !_save()) return;
        redirect(action: "create");
    }

    def save() {
        if (params.year) {
            if (!_save()) return

        }
        else if (!TheoryReferenceVote.findAllByUserAndTheory(session.user,session.theory)) {
            flash.message = "Please add or select at least one reference"
            redirect(action:"create")
            return
        }
        redirect(action: "create", controller: "theoryTheoryLink");

    }

    def _save() {
        params.creator = session.user;
        def reference = new Reference(params);
        if (!reference.save(flush: true)) {
            flash.message = "Error creating reference"
            render(view:"create", model:[reference: reference])
            return false
        } else {
            def theoryReferenceVoteInstance = new TheoryReferenceVote(user: session.user, theory: session.theory, reference: reference)
            if (!theoryReferenceVoteInstance.save(flush: true)) {
                render(view: "create", model: [theoryReferenceVoteInstance: theoryReferenceVoteInstance, reference: reference])
                return  false
            }
        }
        true
    }

    def create() {
        session.step = 3
        def myReferences = TheoryReferenceVote.findAllByUserAndTheory(session.user,session.theory).collect {it.reference}
        [theoryReferenceVoteInstance: new TheoryReferenceVote(params),myReferences:myReferences]
    }

    //nothing else is used here



    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [theoryReferenceVoteInstanceList: TheoryReferenceVote.list(params), theoryReferenceVoteInstanceTotal: TheoryReferenceVote.count()]
    }



    def show(Long id) {
        def theoryReferenceVoteInstance = TheoryReferenceVote.get(id)
        if (!theoryReferenceVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "list")
            return
        }

        [theoryReferenceVoteInstance: theoryReferenceVoteInstance]
    }

    def edit(Long id) {
        def theoryReferenceVoteInstance = TheoryReferenceVote.get(id)
        if (!theoryReferenceVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "list")
            return
        }

        [theoryReferenceVoteInstance: theoryReferenceVoteInstance]
    }

    def update(Long id, Long version) {
        def theoryReferenceVoteInstance = TheoryReferenceVote.get(id)
        if (!theoryReferenceVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (theoryReferenceVoteInstance.version > version) {
                theoryReferenceVoteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote')] as Object[],
                        "Another user has updated this TheoryReferenceVote while you were editing")
                render(view: "edit", model: [theoryReferenceVoteInstance: theoryReferenceVoteInstance])
                return
            }
        }

        theoryReferenceVoteInstance.properties = params

        if (!theoryReferenceVoteInstance.save(flush: true)) {
            render(view: "edit", model: [theoryReferenceVoteInstance: theoryReferenceVoteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), theoryReferenceVoteInstance.id])
        redirect(action: "show", id: theoryReferenceVoteInstance.id)
    }

    def delete(Long id) {
        def theoryReferenceVoteInstance = TheoryReferenceVote.get(id)
        if (!theoryReferenceVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "list")
            return
        }

        try {
            theoryReferenceVoteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theoryReferenceVote.label', default: 'TheoryReferenceVote'), id])
            redirect(action: "show", id: id)
        }
    }


}
