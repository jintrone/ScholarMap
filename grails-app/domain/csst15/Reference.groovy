package csst15

import csst15.security.User

class Reference {
    User creator
    Integer year
    String citation
    String content

    static constraints = {
    }

    static mapping = {
        content type: "text"
    }
}
