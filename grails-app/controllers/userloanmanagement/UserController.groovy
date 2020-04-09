package userloanmanagement

import am.neovision.User
import am.neovision.UserService
import am.neovision.dto.SignUpRequestCommand
import am.neovision.dto.UserInfo
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonBuilder
import org.grails.web.json.JSONObject
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam


class UserController{

    static responseFormats = ['json']

    private UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    static allowedMethods = [
            register: "POST",
            info: 'GET',
            updateUser: 'POST',
            resetPassoword: 'POST',
            changePasswordByEmailCode: 'POST'
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


    @Secured(['permitAll'])
    def resetPassoword(@RequestParam String userEmail){
        respond new JSONObject("{responseMessage:"+userService.resetPassword(userEmail)+"}")
    }

    @Secured(['permitAll'])
    def changePasswordByEmailCode(@PathVariable long emailCode,@RequestParam String password){
        respond userService.changePassword(emailCode,password)
    }

    @Secured('permitAll')
    def activateProfile(@PathVariable String emailCode){
        respond new JSONObject("{status:"+userService.activateProfile(emailCode)+"}")
    }

    @Secured('ROLE_ADMIN')
    def getAllUsers(){
        respond userService.getAllUsers()
    }

    @Secured('ROLE_ADMIN')
    def activateUserById(@PathVariable long id){
        respond userService.activateUserById(id)
    }

    @Secured('ROLE_ADMIN')
    def deactivateUserById(@PathVariable long id){
       respond userService.deactivateUserById(id)
    }

}
