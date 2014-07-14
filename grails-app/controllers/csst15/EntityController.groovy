package csst15

import org.apache.shiro.SecurityUtils

class EntityController {

    def index() {}

    def removeFromUser = {

        User u = User.findByEmail(SecurityUtils.subject.principal)
        Entity e = Entity.get(params.id)

        u.removeFromEntities(e)
        e.removeFromUsers(u)
        u.save(flush: true)
        e.save()
        render("true")
    }

    def create = {
        User u = User.findByEmail(SecurityUtils.subject.principal as String)
        Entity entity = null
        switch (params.type) {
            case "field":
                entity = new Field(name: params.name, description:params.description)
                break
            case "venue":
                entity = new Venue(name: params.name, description:params.description, kind:params.kind)
                break
            case "method":
                entity = new Method(name: params.name, description:params.description)
                break
            case "theory":
                entity = new Theory(name: params.name, description:params.description)
                break
        }

        entity.addToUsers(u)
        if (!entity.save(flush: true)) {
            println(entity.errors)
            return render("There was an error saving the field")
        }

        u.addToEntities(entity)
        u.save(flush: true)
        return redirect(action:"update",params:[type:params.type,entity:entity.id])
    }

    def update = {
        [type:params.type,entity:Entity.get(params.entity)]
    }

    def updateEntity = {
        Entity e= Entity.get(params.entityId)
        if (params.name) {
            e.name =params.name
            e.save(flush:true)
            return render(e.name)
        }
        if (params.description) {
            e.description = params.description
            e.save(flush:true)
            return render(e.description)
        }
    }

    def addDetail = {
        Entity ent = Entity.get(params.entityId)
        ent.description=params.description
        if (params.type == "venue") {
            ent.venueType = params.venueType
        }
        ent.save(flush:true)
        redirect(controller: "user",action:"profile")
    }





    def availableByType = {
        User u = User.findByEmail(SecurityUtils.subject.principal)
        Class c = Entity.resolve(params.type)
        render(template: "/common/cloud", model: [user: u, data: c.all, type: params.type])
    }

    def addToUser = {
        User u = User.findByEmail(SecurityUtils.subject.principal)
        u.addToEntities(Entity.get(params.id))
        u.save(flush: true)
        render("true")
    }

    def summary = {
        Class c = Entity.resolve(params.type)
        def result = c.executeQuery("select entity, count(users) as popularity from ${c.simpleName} as entity left join entity.users as users group by entity.id order by popularity desc")
        print result
        [data:result,type:params.type]
    }

    def detail = {
        Entity e = Entity.get(params.entityId)
        def result = ReferenceVote.executeQuery("select distinct vote.reference from ReferenceVote as vote where vote.entity.id = ${e.id} ")
        [data:result,entity:e]
    }

}
