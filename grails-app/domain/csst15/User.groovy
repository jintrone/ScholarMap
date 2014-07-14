package csst15

import grails.converters.JSON
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

class User {

    String passwordHash

    String firstName
    String lastName
    String email
    String institution
    String position
    String showModal

    static hasMany = [roles: Role, permissions: String, references:ReferenceVote,entities:Entity]

    static constraints = {
        firstName(nullable: true)
        lastName(nullable: true)
        email(nullable:false,blank:false,unique:true)
        institution(nullable:true)
        position(nullable:true)
        showModal(nullable:true)

    }

    Collection getEntitiesOfType(Class clazz) {
       return entities.findAll {
           it.class == clazz
       }
    }

    def getPrefs() {
        new JsonSlurper().parseText(showModal?:"{}")
    }

    def setPrefs(def prefs) {
        showModal = JsonOutput.toJson(prefs)
    }

}
