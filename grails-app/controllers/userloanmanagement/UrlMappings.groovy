package userloanmanagement

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/api/user/info"(controller: "user",action: "whoAmI")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
