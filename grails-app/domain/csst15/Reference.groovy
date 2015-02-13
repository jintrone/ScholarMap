package csst15

class Reference {

//    User creator
    String author
    Integer year
    String text

    static constraints = {
    }

    static mapping = {
        text type: "text"
    }
}
