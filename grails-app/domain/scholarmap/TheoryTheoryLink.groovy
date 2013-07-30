package scholarmap

class TheoryTheoryLink {

    String type
    User user
    Date dateCreated
    Theory fromTheory
    Theory toTheory

    static belongsTo=[User]

    static constraints = {
    }
}
