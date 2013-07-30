package cssttheorybuilder

class TheoryReferenceVote {

    User user
    Theory theory
    Reference reference
    Date dateCreated

    static belongsTo = [Theory,Reference,User]

    static constraints = {
    }

    static mapping = {
        autoTimestamp true
    }
}
