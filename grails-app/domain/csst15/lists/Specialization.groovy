package csst15.lists

class Specialization {
    String title

    static constraints = {
        title blank: false, unique: true
    }
}
