package csst15.lists

class Position {
    String name

    static constraints = {
        name blank: false, unique: true
    }

    static mapping = {
        version false
    }
}
