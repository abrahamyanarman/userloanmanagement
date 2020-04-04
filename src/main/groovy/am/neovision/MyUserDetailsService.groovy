package am.neovision

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import org.springframework.dao.DataAccessException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Transactional(readOnly = true)
class MyUserDetailsService implements GrailsUserDetailsService{
    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
     * one role, so we give a user with no granted roles this one which gets
     * past that restriction but doesn't grant anything.
     */
    static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]

    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException, DataAccessException {
        return loadUserByUsername(username)
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = User.findByUsername(username)
        if (!user) throw new NoStackUsernameNotFoundException()
        Set<Authority> roles = user.authorities
        def authorities = roles.collect {
            new SimpleGrantedAuthority(it.authority)
        }
        return new MyUserDetails(user.username,user.password, user.enabled, !user.accountExpired,!user.passwordExpired,
                !user.accountLocked,authorities?: NO_ROLES,user.id,user.firstName+" "+user.lastName,user.firstName,
                user.lastName,user.userEmail,user.uuid)
    }
}
