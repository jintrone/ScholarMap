package csst15

//import org.apache.shiro.SecurityUtils

class ReferenceController {

//    def index() {}
//
//    def availablereferences = {
//        println(params)
//        User u = User.findByEmail(SecurityUtils.subject.principal)
//        Entity e = Entity.get(params.entityId)
//
//        Collection<Reference> existing =new ArrayList()
//
//
//        Collection<Long> mine = ReferenceVote.findAllByUserAndEntity(u,e)*.reference?.id
//        render(template:"/common/referenceList",model:[references:Reference.list(sort:"author"),exclude:mine,interactive:params.interactive,callback:params.callback])
//    }
//
//
//    def create = {
//        User u = User.findByEmail(SecurityUtils.subject.principal as String)
//        Reference r = new Reference(author:params.author,year:params.year,creator:u,text:params.completeReference)
//        r.save(flush:true)
//
//        ReferenceVote vote = new ReferenceVote(reference: r,entity: Entity.get(params.entityId), user: u)
//        vote.save(flush:true)
//
//        u.addToReferences(vote)
//        u.save()
//        render("true")
//
//
//    }
}
