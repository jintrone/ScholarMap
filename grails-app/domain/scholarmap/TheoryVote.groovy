package scholarmap

class TheoryVote {

    Date dateCreated
    Theory theory
    User user

    static belongsTo = [Theory,User]



    static constraints = {
    }

    static mapping = {
        autoTimestamp true
    }
}
