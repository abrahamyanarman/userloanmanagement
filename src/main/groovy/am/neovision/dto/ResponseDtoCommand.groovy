package am.neovision.dto


import org.springframework.http.HttpStatus

class ResponseDtoCommand  {
    String message
    String error
    HttpStatus status

    ResponseDtoCommand() {
    }

    ResponseDtoCommand(String message, String error, HttpStatus status) {
        this.message = message
        this.error = error
        this.status = status
    }

    ResponseDtoCommand(String message, HttpStatus status) {
        this.message = message
        this.status = status
    }


}
