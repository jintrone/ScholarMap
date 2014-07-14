package csst15

import org.apache.shiro.SecurityUtils

class ReferenceVoteController {


    def myreferences = {
        render(template: "/common/myentityreferences", model: [entity: Entity.get(params.entityId)])
    }
    def create = {
        User u = User.findByEmail(SecurityUtils.subject.principal)
        Entity e = Entity.get(params.entityId)
        Reference r = Reference.get(params.id)
        ReferenceVote vote = ReferenceVote.findByUserAndEntityAndReference(u, e, r)
        if (!vote) {

            vote = new ReferenceVote(user: u, entity: e, reference: r)
            vote.save()

            u.addToReferences(vote)
            u.save(flush: true)
        }
        render("true")


    }

    def remove = {
        User u = User.findByEmail(SecurityUtils.subject.principal)
        ReferenceVote vote = ReferenceVote.get(params.id)
        u.removeFromReferences(vote)
        ReferenceVote.deleteAll(vote)
        u.save(flush: true)
        render("true")
    }
}
