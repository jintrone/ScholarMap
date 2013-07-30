package cssttheorybuilder

class User {

    String name
    String email
    Date dateCreated

    static hasMany = [theories: Theory, theoryVotes: TheoryVote, references:Reference, theoryReferenceVotes: TheoryReferenceVote]

    static constraints = {
        name(blank:false)
        email(email:true,blank:false)


    }

    static mapping = {
        autoTimestamp true
    }

    String toString() {
        name
    }
}
