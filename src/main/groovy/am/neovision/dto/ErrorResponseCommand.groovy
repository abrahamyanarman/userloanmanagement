package am.neovision.dto

import grails.validation.Validateable;


class ErrorResponseCommand implements Validateable{
   private String message


    ErrorResponseCommand(String message) {
        this.message = message
    }
}
