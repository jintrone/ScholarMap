package cssttheorybuilder



import org.junit.*
import grails.test.mixin.*

@TestFor(ReferenceController)
@Mock(Reference)
class ReferenceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/reference/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.referenceInstanceList.size() == 0
        assert model.referenceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.referenceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.referenceInstance != null
        assert view == '/reference/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/reference/show/1'
        assert controller.flash.message != null
        assert Reference.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/reference/list'

        populateValidParams(params)
        def reference = new Reference(params)

        assert reference.save() != null

        params.id = reference.id

        def model = controller.show()

        assert model.referenceInstance == reference
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/reference/list'

        populateValidParams(params)
        def reference = new Reference(params)

        assert reference.save() != null

        params.id = reference.id

        def model = controller.edit()

        assert model.referenceInstance == reference
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/reference/list'

        response.reset()

        populateValidParams(params)
        def reference = new Reference(params)

        assert reference.save() != null

        // test invalid parameters in update
        params.id = reference.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/reference/edit"
        assert model.referenceInstance != null

        reference.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/reference/show/$reference.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        reference.clearErrors()

        populateValidParams(params)
        params.id = reference.id
        params.version = -1
        controller.update()

        assert view == "/reference/edit"
        assert model.referenceInstance != null
        assert model.referenceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/reference/list'

        response.reset()

        populateValidParams(params)
        def reference = new Reference(params)

        assert reference.save() != null
        assert Reference.count() == 1

        params.id = reference.id

        controller.delete()

        assert Reference.count() == 0
        assert Reference.get(reference.id) == null
        assert response.redirectedUrl == '/reference/list'
    }
}
