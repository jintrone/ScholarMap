package csst15.lists

class Department {
    String title

    static constraints = {
        title blank: false, unique: true
    }
}
