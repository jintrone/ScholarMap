package ScholarMap



import org.junit.*
import grails.test.mixin.*

@TestFor(TheoryReferenceVoteController)
@Mock(TheoryReferenceVote)
class TheoryReferenceVoteControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theoryReferenceVote/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.theoryReferenceVoteInstanceList.size() == 0
        assert model.theoryReferenceVoteInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.theoryReferenceVoteInstance != null
    }

    void testSave() {
        controller.save()

        assert model.theoryReferenceVoteInstance != null
        assert view == '/theoryReferenceVote/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theoryReferenceVote/show/1'
        assert controller.flash.message != null
        assert TheoryReferenceVote.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryReferenceVote/list'

        populateValidParams(params)
        def theoryReferenceVote = new TheoryReferenceVote(params)

        assert theoryReferenceVote.save() != null

        params.id = theoryReferenceVote.id

        def model = controller.show()

        assert model.theoryReferenceVoteInstance == theoryReferenceVote
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryReferenceVote/list'

        populateValidParams(params)
        def theoryReferenceVote = new TheoryReferenceVote(params)

        assert theoryReferenceVote.save() != null

        params.id = theoryReferenceVote.id

        def model = controller.edit()

        assert model.theoryReferenceVoteInstance == theoryReferenceVote
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryReferenceVote/list'

        response.reset()

        populateValidParams(params)
        def theoryReferenceVote = new TheoryReferenceVote(params)

        assert theoryReferenceVote.save() != null

        // test invalid parameters in update
        params.id = theoryReferenceVote.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theoryReferenceVote/edit"
        assert model.theoryReferenceVoteInstance != null

        theoryReferenceVote.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theoryReferenceVote/show/$theoryReferenceVote.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theoryReferenceVote.clearErrors()

        populateValidParams(params)
        params.id = theoryReferenceVote.id
        params.version = -1
        controller.update()

        assert view == "/theoryReferenceVote/edit"
        assert model.theoryReferenceVoteInstance != null
        assert model.theoryReferenceVoteInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theoryReferenceVote/list'

        response.reset()

        populateValidParams(params)
        def theoryReferenceVote = new TheoryReferenceVote(params)

        assert theoryReferenceVote.save() != null
        assert TheoryReferenceVote.count() == 1

        params.id = theoryReferenceVote.id

        controller.delete()

        assert TheoryReferenceVote.count() == 0
        assert TheoryReferenceVote.get(theoryReferenceVote.id) == null
        assert response.redirectedUrl == '/theoryReferenceVote/list'
    }
}
