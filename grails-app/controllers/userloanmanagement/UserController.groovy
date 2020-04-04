package userloanmanagement

import am.neovision.UserService
import am.neovision.dto.SignUpRequestCommand
import am.neovision.dto.UserInfo
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

import javax.websocket.server.PathParam

class UserController {

    static responseFormats = ['json']

    private UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    static allowedMethods = [
            register: "POST",
            info: 'GET',
            updateUser: 'POST'
    ]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def register(@RequestBody SignUpRequestCommand signUpRequestCommand) {

        def response = userService.createNewUser(signUpRequestCommand)
        respond response
    }

    def whoAmI(){
        respond userService.whoAmI(request)
    }

    def info(@PathVariable long id){
        respond userService.userInfoById(id)
    }

    def updateUser(@RequestBody UserInfo userInfo){
        respond userService.updateUser(userInfo)
    }

}
