package ScholarMap



import org.junit.*
import grails.test.mixin.*

@TestFor(TheoryVoteController)
@Mock(TheoryVote)
class TheoryVoteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theoryVote/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.theoryVoteInstanceList.size() == 0
        assert model.theoryVoteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.theoryVoteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.theoryVoteInstance != null
        assert view == '/theoryVote/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theoryVote/show/1'
        assert controller.flash.message != null
        assert TheoryVote.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryVote/list'

        populateValidParams(params)
        def theoryVote = new TheoryVote(params)

        assert theoryVote.save() != null

        params.id = theoryVote.id

        def model = controller.show()

        assert model.theoryVoteInstance == theoryVote
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryVote/list'

        populateValidParams(params)
        def theoryVote = new TheoryVote(params)

        assert theoryVote.save() != null

        params.id = theoryVote.id

        def model = controller.edit()

        assert model.theoryVoteInstance == theoryVote
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryVote/list'

        response.reset()

        populateValidParams(params)
        def theoryVote = new TheoryVote(params)

        assert theoryVote.save() != null

        // test invalid parameters in update
        params.id = theoryVote.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theoryVote/edit"
        assert model.theoryVoteInstance != null

        theoryVote.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theoryVote/show/$theoryVote.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theoryVote.clearErrors()

        populateValidParams(params)
        params.id = theoryVote.id
        params.version = -1
        controller.update()

        assert view == "/theoryVote/edit"
        assert model.theoryVoteInstance != null
        assert model.theoryVoteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theoryVote/list'

        response.reset()

        populateValidParams(params)
        def theoryVote = new TheoryVote(params)

        assert theoryVote.save() != null
        assert TheoryVote.count() == 1

        params.id = theoryVote.id

        controller.delete()

        assert TheoryVote.count() == 0
        assert TheoryVote.get(theoryVote.id) == null
        assert response.redirectedUrl == '/theoryVote/list'
    }
}
