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

        "/user/edit/$username" {
            controller = "admin"
            action = "editUserProfile"
        }

        "/user/changePasswordPage/$username" {
            controller = "user"
            action = "changePasswordPage"
        }

        "/user/changePassword" {
            controller = "user"
            action = "changePassword"
        }

        "/user/manipulateFieldVisibility" {
            controller = "user"
            action = "manipulateFieldVisibility"
        }

        "/edit/$username" {
            controller = "user"
            action = "edit"
        }

        "/user/update" {
            controller = "user"
            action = "update"
        }

        "/user/update" {
            controller = "user"
            action = "update"
        }

        "/api/v1/people/graphs/force-directed" {
            controller = "graph"
            action = "peopleGraph"
        }

        "/api/v1/references/graphs/force-directed" {
            controller = "graph"
            action = "charGraph"
        }
    }
}
