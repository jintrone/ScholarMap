package csst15

import groovy.transform.ToString

@ToString(includeNames = true)
class ReferenceAuthor implements Serializable {
    int authorOrder
    Reference reference
    Author author

    static constraints = {
        authorOrder nullable: true
        reference nullable: true
        author nullable: true
    }

    static ReferenceAuthor create(Author author, Reference reference, boolean flush = false) {
        def instance = new ReferenceAuthor(author: author, reference: reference)
        instance.save(flush: flush, insert: true)
        instance
    }

    static mapping = {
        id composite: ['reference', 'author']
        version false
    }
}
