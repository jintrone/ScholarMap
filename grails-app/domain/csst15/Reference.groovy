package csst15

import csst15.security.User

class Reference {
    User creator
    Integer year
    String citation
    String content
    String hash

    static constraints = {
        citation blank: false, maxSize: 500
        content nullable: true, maxSize: 5000
        hash blank: false
    }

    static mapping = {
        content type: "text"
    }
}
