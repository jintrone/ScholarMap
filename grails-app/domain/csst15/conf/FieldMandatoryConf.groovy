package csst15.conf

class FieldMandatoryConf {
    String fieldName
    Boolean isMandatory

    static constraints = {
        fieldName blank: false, unique: true
    }

    static mapping = {
        version false
    }
}
