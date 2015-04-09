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
        "/not-found"(view: '/not-found')

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")

        "/areas" {
            controller = "home"
            action = "areas"
        }

        "/theories" {
            controller = "home"
            action = "theories"
        }

        "/venues" {
            controller = "home"
            action = "venues"
        }

        "/methods" {
            controller = "home"
            action = "methods"
        }

        "/references" {
            controller = "home"
            action = "references"
        }

        "/user/$username" {
            controller = "user"
            action = "profile"
        }

        "/user/edit/$username" {
            controller = "admin"
            action = "editUserProfile"
        }

        "/user/addInterest" {
            controller = "user"
            action = "addInterest"
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

        "/user/interest/$username" {
            controller = "user"
            action = "interests"
        }

        "/interest/delete" {
            controller = "user"
            action = "deleteInterest"
        }

        "/user/fillRequiredFields" {
            controller = "user"
            action = "fillRequiredFields"
        }

        "/user/updateRequiredFields" {
            controller = "user"
            action = "updateRequiredFields"
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
            action = "refGraph"
        }

        "/api/v1/characteristics/graphs/force-directed" {
            controller = "graph"
            action = "charGraph"
        }
    }
}
