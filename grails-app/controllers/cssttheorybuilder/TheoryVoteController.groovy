package cssttheorybuilder

import org.springframework.dao.DataIntegrityViolationException

class TheoryVoteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def vote() {

        def theories = TheoryVote.findAllByUserNotEqual(session.user).collect {t -> t.theory}
        def myTheories =  TheoryVote.findAllByUserEqual(session.user).collect {t->t.theory}
        session.theoryCount = myTheories.size()
        session.step = 1
        if (theories.isEmpty()) {
            redirect(action:"create", model: model)
        } else {
            [theories: theories, myTheories:myTheories]
        }
    }

    def saveVote() {
        def theoryInstance = Theory.find(theoryId);
        def vote = new TheoryVote(user: session.user, theory: theoryInstance)
        vote.save()
        session.theory = theoryInstance;
        flash.message = message(code: 'default.created.message', args: [message(code: 'theory.label', default: 'Theory'), theoryInstance.id])
        redirect(action: "vote", controller: "theoryReferenceVote")
    }

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [theoryVoteInstanceList: TheoryVote.list(params), theoryVoteInstanceTotal: TheoryVote.count()]
    }

    def create() {
        [theoryVoteInstance: new TheoryVote(params), myTheories: TheoryVote.findAllByUserEqual(session.user).collect {t->t.theory}]
    }

    def save() {
        def theoryVoteInstance = new TheoryVote(params)
        if (!theoryVoteInstance.save(flush: true)) {
            render(view: "create", model: [theoryVoteInstance: theoryVoteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), theoryVoteInstance.id])
        redirect(action: "show", id: theoryVoteInstance.id)
    }

    //everything else is boiler plate

    def show(Long id) {
        def theoryVoteInstance = TheoryVote.get(id)
        if (!theoryVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "list")
            return
        }

        [theoryVoteInstance: theoryVoteInstance]
    }

    def edit(Long id) {
        def theoryVoteInstance = TheoryVote.get(id)
        if (!theoryVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "list")
            return
        }

        [theoryVoteInstance: theoryVoteInstance]
    }

    def update(Long id, Long version) {
        def theoryVoteInstance = TheoryVote.get(id)
        if (!theoryVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (theoryVoteInstance.version > version) {
                theoryVoteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'theoryVote.label', default: 'TheoryVote')] as Object[],
                        "Another user has updated this TheoryVote while you were editing")
                render(view: "edit", model: [theoryVoteInstance: theoryVoteInstance])
                return
            }
        }

        theoryVoteInstance.properties = params

        if (!theoryVoteInstance.save(flush: true)) {
            render(view: "edit", model: [theoryVoteInstance: theoryVoteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), theoryVoteInstance.id])
        redirect(action: "show", id: theoryVoteInstance.id)
    }

    def delete(Long id) {
        def theoryVoteInstance = TheoryVote.get(id)
        if (!theoryVoteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "list")
            return
        }

        try {
            theoryVoteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theoryVote.label', default: 'TheoryVote'), id])
            redirect(action: "show", id: id)
        }
    }


}
