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
        "/api/user/updateUserPhoto/$id"(controller: "user",action: "updateUserPhoto")
        "/api/user/getUserPhoto/$id"(controller: "user",action: "getUserPhoto")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
