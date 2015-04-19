package csst15

class AutoCompleteTagLib {
    static namespace = "csst"

    def autocomplete = { attrs ->
        def name, clazz, action, title = ""

        if (attrs.id == null) {
            throwTagError("Tag [autoComplete] is missing required attribute [id]")
        }

        if (!attrs.controller) {
            attrs.controller = 'autoComplete'
        }

        if (!attrs.max) {
            attrs.max = 10
        }

        if (!attrs.value) {
            attrs.value = ""
        }

        if (!attrs.order) {
            attrs.order = "asc"
        }

        if (attrs.action) {
            action = "${attrs.action}"
        }

        if (attrs.name) {
            name = "${attrs.name}"
        } else {
            name = "${attrs.id}"
        }

        if (attrs.class) {
            clazz = "${attrs.class}"
        }

        if (attrs.title) {
            title = "${attrs.title}"
        }

        out << render(template: '/autoComplete/autoComplete', model: [attrs: attrs, name: name, clazz: clazz])
    }

    def autoField = { attrs, body ->
        def title = attrs.remove("title")
        out << """<input title="${title}" """
        attrs.each { k, v ->
            out << k << "=\"" << v << "\" "
        }
        out << "/>"
    }
}
