package cssttheorybuilder

class Theory {

    String name
    String description
    Date dateCreated
    User creator

    static hasMany = [references:Reference, votes:TheoryVote, referenceVotes:TheoryReferenceVote, linksFrom: Theory, linksTo: Theory]
    static belongsTo = User
    static mapping = {
        references joinTable: [name: "references_theories", key: 'theory_id' ]
        linksFrom joinTable: [name: "theory_theory_link", key: "from_theory_id"]
        linksTo joinTable: [name: "theory_theory_link", key: "to_theory_id"]
    }

    static constraints = {
        name(blank:false)
        description(maxSize:1000,nullable:true)
    }

    String toString() {
        name
    }

    boolean isConnected(theory,user) {
        TheoryTheoryLink.findByFromTheoryAndToTheoryAndUser(theory,this,user) ||
                TheoryTheoryLink.findByFromTheoryAndToTheoryAndUser(this,theory,user)
    }


}
