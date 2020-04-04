package userloanmanagement

class UrlMappings {

    static mappings = {
        "/api/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/api/user/info"(controller: "user",action: "whoAmI")
        "/api/user/info/$id"(controller: "user",action: "info")
        "/api/user/updateUser"(controller: "user",action: "updateUser")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
