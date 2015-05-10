package csst15

class AuthorTagLib {
    static defaultEncodeAs = [taglib: 'html']
    static namespace = "csst"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def author = { attrs ->
        def reference = attrs.reference
        def authors = ReferenceAuthor.findAllByReference(reference)?.author
        def result = ""

        authors.each { a ->
            result += "${a.lastName} ${a.firstName.getAt(0)}.; "
        }

        out << result
    }
}
