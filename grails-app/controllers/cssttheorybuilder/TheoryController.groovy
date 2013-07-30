package cssttheorybuilder

import org.springframework.dao.DataIntegrityViolationException

class TheoryController {

    static allowedMethods = [save: "POST", foo: "POST", update: "POST", delete: "POST"]


    def vote() {

        def myTheories = TheoryVote.findAllByUser(session.user).collect { t -> t.theory }
        def theories = !myTheories.isEmpty()?Theory.findAll("from Theory t where t not in :theories",[theories:myTheories]):Theory.findAll()
        if (params?.order == "desc") {
            theories.sort() {a,b -> b.name <=> a.name}
        } else {
            theories.sort() {a,b -> a.name <=> b.name}
        }





        if (session.step ==4 && session.theoryCount <myTheories.size()) {
            switch (myTheories.size()) {
                case 0..2:
                    flash.info = "Awesome, thanks!  ${3-myTheories.size()} more to go!"
                    break;

                case 3:
                    flash.info = "You could stop now, if you wanted. But, maybe do a few more?  The more data we have, the more awesome this will be!"
                    flash.done = "Thank you!  You've hit the target of 3 theories!"
                    break;

                case 4:
                    flash.info = "YOU ROCK! Keep going, you're on a roll!"
                    break;

                case 6:
                    flash.info = "I'm starting to really like you."
                    break;

                case 8:
                    flash.info = "Is it hot in here or is it just you?"
                    break;

                case 10:
                    flash.info = "Holy cow.  Your mind is a maze of amazing!"
                    break;

                case 12:
                    flash.info = "<error>YOU HAVE BROKEN THE ACCOLADE ENGINE.</error> :-D"
                    break;

            }
        }

        session.theoryCount = myTheories.size()
        session.step = 1
        session.theory=null


        if (theories.isEmpty()) {
            redirect(action: "create")
        } else {
            [theoryInstanceList: theories, myTheories: myTheories]
        }
    }



    def saveVote(Long id) {
        if (!id) {
            redirect(action: "create")
            return
        } else {
            def theoryInstance = Theory.get(id);
            def vote = new TheoryVote(user: session.user, theory: theoryInstance)
            vote.save()
            session.theory = theoryInstance;
            flash.message = message(code: 'default.created.message', args: [message(code: 'theory.label', default: 'Theory'), theoryInstance.id])
            redirect(action: "vote", controller: "theoryReferenceVote")
        }
    }

    def create() {
        [theoryInstance: new Theory(params), myTheories: TheoryVote.findAllByUser(session.user).collect { t -> t.theory }]
    }

    def save() {
        def theoryInstance = new Theory(params)
        theoryInstance.creator = session.user;
        if (!theoryInstance.save(flush: true)) {
            render(view: "create", model: [theoryInstance: theoryInstance])
            return
        }
        saveVote(theoryInstance.id)

    }

    // nothing else is really used here

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [theoryInstanceList: Theory.list(params), theoryInstanceTotal: Theory.count()]
    }





    def show(Long id) {
        def theoryInstance = Theory.get(id)
        if (!theoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "list")
            return
        }

        [theoryInstance: theoryInstance]
    }

    def edit(Long id) {
        def theoryInstance = Theory.get(id)
        if (!theoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "list")
            return
        }

        [theoryInstance: theoryInstance]
    }

    def update(Long id, Long version) {
        def theoryInstance = Theory.get(id)
        if (!theoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (theoryInstance.version > version) {
                theoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'theory.label', default: 'Theory')] as Object[],
                        "Another user has updated this Theory while you were editing")
                render(view: "edit", model: [theoryInstance: theoryInstance])
                return
            }
        }

        theoryInstance.properties = params

        if (!theoryInstance.save(flush: true)) {
            render(view: "edit", model: [theoryInstance: theoryInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'theory.label', default: 'Theory'), theoryInstance.id])
        redirect(action: "show", id: theoryInstance.id)
    }

    def delete(Long id) {
        def theoryInstance = Theory.get(id)
        if (!theoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "list")
            return
        }

        try {
            theoryInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'theory.label', default: 'Theory'), id])
            redirect(action: "show", id: id)
        }
    }




}
