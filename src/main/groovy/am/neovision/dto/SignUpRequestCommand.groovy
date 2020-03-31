package am.neovision.dto


import grails.validation.Validateable
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class SignUpRequestCommand implements Validateable{
    String firstName
    String lastName
    @NotBlank(message = "Email is required")
    @Email(regexp = "(^([^@\\s]){2,32})+(@([^@\\s]){1,15})+\\.([^@\\s]){2,15}",
           message = "Enter your email address in the following format address@example.com")
    String email
    @NotBlank(message = "User name is required")
    String username
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password should be 8 - 16 characters long.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&+=])(?=\\S+\$).{8,}\$")
    String password
    String repassword
    String photoUri
    List<Long> roles

    static constraints = {
        username nullable: false, blank: false, unique: true
        password nullable: false,blank: false, password:true

    }
}