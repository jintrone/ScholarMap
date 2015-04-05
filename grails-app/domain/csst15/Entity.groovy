package csst15

import csst15.constants.EntityType
import csst15.security.User


class Entity {
    String name
    String description
    EntityType type
    static belongsTo = User
    static hasMany = [users: User, references: ReferenceVote]

    static constraints = {
        description nullable: true
        name blank: false, unique: true
        type blank: false
        users nullable: true
        references nullable: true
    }

    static mapping = {
        users cascade: 'all-delete-orphan'
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
}
