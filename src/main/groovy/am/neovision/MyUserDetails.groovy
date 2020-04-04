package am.neovision

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class MyUserDetails extends GrailsUser {
    final String fullName
    String firstName
    String lastName
    String userEmail
    String uuid

    MyUserDetails(String username, String password, boolean enabled,
                  boolean accountNonExpired, boolean credentialsNonExpired,
                  boolean accountNonLocked,
                  Collection<GrantedAuthority> authorities,
                  long id, String fullName,String firstName,String lastName,String userEmail,String uuid) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id)
        this.firstName = firstName
        this.lastName = lastName
        this.userEmail = userEmail

        this.fullName = fullName
        this.uuid = uuid
    }
}
