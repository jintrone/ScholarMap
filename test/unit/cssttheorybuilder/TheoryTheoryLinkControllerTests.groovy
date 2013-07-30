package scholarmap



import org.junit.*
import grails.test.mixin.*

@TestFor(TheoryTheoryLinkController)
@Mock(TheoryTheoryLink)
class TheoryTheoryLinkControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theoryTheoryLink/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.theoryTheoryLinkInstanceList.size() == 0
        assert model.theoryTheoryLinkInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.theoryTheoryLinkInstance != null
    }

    void testSave() {
        controller.save()

        assert model.theoryTheoryLinkInstance != null
        assert view == '/theoryTheoryLink/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theoryTheoryLink/show/1'
        assert controller.flash.message != null
        assert TheoryTheoryLink.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryTheoryLink/list'

        populateValidParams(params)
        def theoryTheoryLink = new TheoryTheoryLink(params)

        assert theoryTheoryLink.save() != null

        params.id = theoryTheoryLink.id

        def model = controller.show()

        assert model.theoryTheoryLinkInstance == theoryTheoryLink
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryTheoryLink/list'

        populateValidParams(params)
        def theoryTheoryLink = new TheoryTheoryLink(params)

        assert theoryTheoryLink.save() != null

        params.id = theoryTheoryLink.id

        def model = controller.edit()

        assert model.theoryTheoryLinkInstance == theoryTheoryLink
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theoryTheoryLink/list'

        response.reset()

        populateValidParams(params)
        def theoryTheoryLink = new TheoryTheoryLink(params)

        assert theoryTheoryLink.save() != null

        // test invalid parameters in update
        params.id = theoryTheoryLink.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theoryTheoryLink/edit"
        assert model.theoryTheoryLinkInstance != null

        theoryTheoryLink.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theoryTheoryLink/show/$theoryTheoryLink.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theoryTheoryLink.clearErrors()

        populateValidParams(params)
        params.id = theoryTheoryLink.id
        params.version = -1
        controller.update()

        assert view == "/theoryTheoryLink/edit"
        assert model.theoryTheoryLinkInstance != null
        assert model.theoryTheoryLinkInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theoryTheoryLink/list'

        response.reset()

        populateValidParams(params)
        def theoryTheoryLink = new TheoryTheoryLink(params)

        assert theoryTheoryLink.save() != null
        assert TheoryTheoryLink.count() == 1

        params.id = theoryTheoryLink.id

        controller.delete()

        assert TheoryTheoryLink.count() == 0
        assert TheoryTheoryLink.get(theoryTheoryLink.id) == null
        assert response.redirectedUrl == '/theoryTheoryLink/list'
    }
}
