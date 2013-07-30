package cssttheorybuilder

class Reference {


    User creator
    int year
    String lastName
    String firstInitial
    String text
    Date dateCreated


    static belongsTo = [User,Theory]
    static hasMany = [theories:Theory, theoryReferenceVotes: TheoryReferenceVote]

    static constraints = {
        text(blank:false)
    }

    static mapping = {
        theories joinTable: [name: "references_theories", key: 'reference_id' ]
    }

    String toString() {
        text
    }
}
