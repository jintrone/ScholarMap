package csst15

import csst15.security.User


class Entity {
    String name
    String description
    String type
    static belongsTo = User
    static hasMany = [users: User, references: ReferenceVote]

    static constraints = {
        description nullable: true
        name blank: false, unique: true
        type blank: false
    }

    static mapping = {
        description type: "text"
    }

    static Class resolve(String s) {
        switch (s) {
            case "field":
                return Field.class
            case "venue":
                return Venue.class
            case "method":
                return Method.class
            case "theory":
                return Theory.class
        }
        return null;
    }

    static String stringify(String s) {
        if (s == "field") {
            return "area"
        } else return s
    }

//   Collection getUserReferences(User u) {
//        Collection result = references.findAll { ReferenceVote vote ->
//            vote.user.id == u.id
//
//        }
//       result?:Collections.emptySet()
//    }
}
