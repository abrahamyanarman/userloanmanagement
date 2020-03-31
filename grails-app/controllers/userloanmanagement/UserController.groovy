package userloanmanagement

import am.neovision.UserService
import am.neovision.dto.SignUpRequestCommand
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.bind.annotation.RequestBody

class UserController {

    static responseFormats = ['json']

    private UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    static allowedMethods = [register: "POST"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def register(@RequestBody SignUpRequestCommand signUpRequestCommand) {

        def response = userService.createNewUser(signUpRequestCommand)
        respond response
    }

    def whoAmI(){
        respond userService.whoAmI(request)
    }

}
