class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home")
        "500"(view: '/error')
        "/access-denied"(view: '/access-denied')

        "/user/$username" {
            controller = "user"
            action = "profile"
        }

        "/api/user"(resource: 'retrieveGraph')
    }
}
