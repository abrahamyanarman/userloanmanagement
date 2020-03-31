package am.neovision.dto

import am.neovision.Authority
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class UserInfoResponseCommand implements Serializable {

    private static final long serialVersionUID = 1


    String firstName
    String lastName
    String email
    String photoUri
    String username
    String token
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Authority> authorities

    UserInfoResponseCommand() {
    }

    UserInfoResponseCommand(String firstName, String lastName, String email, String photoUri, String username, String token, boolean enabled, boolean accountExpired, boolean accountLocked, boolean passwordExpired, Set<Authority> authorities) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.photoUri = photoUri
        this.username = username
        this.token = token
        this.enabled = enabled
        this.accountExpired = accountExpired
        this.accountLocked = accountLocked
        this.passwordExpired = passwordExpired
        this.authorities = authorities
    }
    static constraints = {
        username nullable: false, blank: false, unique: true
        email nullable: false,blank: false, unique: true,email: true
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        photoUri nullable: true,blank: true
    }


    @Override
    String toString() {
        return "UserInfoResponseCommand{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", photoUri='" + photoUri + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", enabled=" + enabled +
                ", accountExpired=" + accountExpired +
                ", accountLocked=" + accountLocked +
                ", passwordExpired=" + passwordExpired +
                ", authorities=[" + authorities.stream().map{ role ->
            role.toString()
        }+"]" +
                '}';
    }
}
