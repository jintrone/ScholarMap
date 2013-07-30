package cssttheorybuilder



import org.junit.*
import grails.test.mixin.*

@TestFor(TheoryController)
@Mock(Theory)
class TheoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/theory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.theoryInstanceList.size() == 0
        assert model.theoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.theoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.theoryInstance != null
        assert view == '/theory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/theory/show/1'
        assert controller.flash.message != null
        assert Theory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/theory/list'

        populateValidParams(params)
        def theory = new Theory(params)

        assert theory.save() != null

        params.id = theory.id

        def model = controller.show()

        assert model.theoryInstance == theory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/theory/list'

        populateValidParams(params)
        def theory = new Theory(params)

        assert theory.save() != null

        params.id = theory.id

        def model = controller.edit()

        assert model.theoryInstance == theory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/theory/list'

        response.reset()

        populateValidParams(params)
        def theory = new Theory(params)

        assert theory.save() != null

        // test invalid parameters in update
        params.id = theory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/theory/edit"
        assert model.theoryInstance != null

        theory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/theory/show/$theory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        theory.clearErrors()

        populateValidParams(params)
        params.id = theory.id
        params.version = -1
        controller.update()

        assert view == "/theory/edit"
        assert model.theoryInstance != null
        assert model.theoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/theory/list'

        response.reset()

        populateValidParams(params)
        def theory = new Theory(params)

        assert theory.save() != null
        assert Theory.count() == 1

        params.id = theory.id

        controller.delete()

        assert Theory.count() == 0
        assert Theory.get(theory.id) == null
        assert response.redirectedUrl == '/theory/list'
    }
}
