package am.neovision.dto

import grails.validation.Validateable


class SignInRequestCommand   implements Validateable{

    String username
    String password
    boolean rememberMe = false



    static constraints = {
        username nullable: false, blank: false, unique: true
        password nullable: false,blank: false, password:true

    }
}
