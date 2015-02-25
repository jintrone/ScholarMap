package csst15

import csst15.security.User

class UserTagLib {
    def springSecurityService

    static defaultEncodeAs = [taglib: 'html']
    static namespace = "csst"
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def userFullName = { attrs ->
        def currentUser = springSecurityService.currentUser as User

        out << currentUser.firstName + " " + currentUser.lastName
    }

    def username = {
        def currentUser = springSecurityService.currentUser as User

        out << currentUser.username
    }
}
