package csst15

import csst15.security.User

class Reference {
    User creator
    Integer year
    String citation
    String content

    static constraints = {
        citation blank: false, maxSize: 500
        content blank: false, maxSize: 5000
    }

    static mapping = {
        content type: "text"
    }
}
