package csst15

class Author {
    String firstName
    String lastName

    static transients = ['fullName']

    static constraints = {
        firstName blank: false
        lastName blank: false
    }
}
