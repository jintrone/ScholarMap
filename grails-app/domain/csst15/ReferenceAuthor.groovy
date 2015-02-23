package csst15

class ReferenceAuthor implements Serializable {
    Reference reference
    Author author
    int order

    static constraints = {
    }

    static mapping = {
        id composite: ['reference', 'author']
        version false
    }
}
