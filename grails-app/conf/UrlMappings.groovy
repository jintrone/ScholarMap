class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home")
        "/about"(view:"/about")
        "500"(view:'/error')
        "/access-denied"(view: '/access-denied')

        "/api/user"(resource: 'retrieveGraph')
	}
}
