package cssttheorybuilder

import org.springframework.dao.DataIntegrityViolationException

class TheoryTheoryLinkController {

    static allowedMethods = [save: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [theoryTheoryLinkInstanceList: TheoryTheoryLink.list(params), theoryTheoryLinkInstanceTotal: TheoryTheoryLink.count()]
    }

    def create() {
        session.step = 4
        def theoryInstanceList = Theory.findAllByIdNotEqual(session.theory.id);
        theoryInstanceList ? [theoryInstanceList: theoryInstanceList] : redirect(action: "vote", controller: "theory")
    }

    def createForUpdate() {
        session.theory = Theory.get(params.theoryId);
        def theoryInstanceList = Theory.findAllByIdNotEqual(session.theory.id);
        if (theoryInstanceList) {
            flash.update = true;
            render(view: "create", model: [theoryInstanceList: theoryInstanceList]);
        } else {
            redirect(action: "vote", controller: "theory")
        }
    }

    def doneUpdate() {
        session.theory = null;
        redirect(action: "vote", controller: "theory")

    }


    def save() {
        def theoryInstanceList = Theory.findAllByIdNotEqual(session.theory.id);
        if (params.theoryIds) {
            for (id in params.theoryIds) {

                Theory theory = Theory.get(id);
                theoryInstanceList.remove(theory)
                if (theory.isConnected(session.theory,session.user)) continue;
                def theoryTheoryLink = new TheoryTheoryLink([user: session.user, type: "overlaps", fromTheory: session.theory, toTheory: theory])
                theoryTheoryLink.save(flush: true);

            }
        }
        for (theory in theoryInstanceList) {
            def link = TheoryTheoryLink.findByFromTheoryAndToTheoryAndUser(theory,session.theory,session.user)
            if (link) link.delete(flush:true)
            link = TheoryTheoryLink.findByFromTheoryAndToTheoryAndUser(session.theory,theory,session.user)
            if (link) link.delete(flush:true)

        }
        if (params.update) {
            redirect(action: "update")
        } else {
            redirect(action: "vote", controller: "theory")
        }
    }

    def show(Long id) {
        def theoryTheoryLinkInstance = TheoryTheoryLink.get(id)
        if (!theoryTheoryLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink'), id])
            redirect(action: "list")
            return
        }

        [theoryTheoryLinkInstance: theoryTheoryLinkInstance]
    }

    def edit(Long id) {
        def theoryTheoryLinkInstance = TheoryTheoryLink.get(id)
        if (!theoryTheoryLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink'), id])
            redirect(action: "list")
            return
        }

        [theoryTheoryLinkInstance: theoryTheoryLinkInstance]
    }

    def update() {
        [theoryInstanceList: TheoryVote.findAllByUser(session.user).collect { t -> t.theory }]
    }

    def delete(Long id) {
        def theoryTheoryLinkInstance = TheoryTheoryLink.get(id)
        if (!theoryTheoryLinkInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink'), id])
            redirect(action: "list")
            return
        }

        try {
            theoryTheoryLinkInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theoryTheoryLink.label', default: 'TheoryTheoryLink'), id])
            redirect(action: "show", id: id)
        }
    }
}
