package csst15

class TheoryController {

    def index() {
        def result = Field.executeQuery("select theory, count(votes) as votecount from Field as theory inner join ReferenceVote.entity as votes group by theory.id")
        println result
    }
}
