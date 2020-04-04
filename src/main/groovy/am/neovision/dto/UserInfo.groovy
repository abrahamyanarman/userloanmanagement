package am.neovision.dto

import am.neovision.Authority
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import grails.validation.Validateable

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class UserInfo implements Serializable, Validateable {

    private static final long serialVersionUID = 1


    Long id
    String uuid
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

    UserInfo() {
    }

    UserInfo(String firstName, String lastName, String email, String photoUri, String username, String token, boolean enabled, boolean accountExpired, boolean accountLocked, boolean passwordExpired, Set<Authority> authorities, String uuid, Long id) {
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
        this.uuid = uuid
        this.id = id
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
