package am.neovision

import grails.events.annotation.Subscriber
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String uuid = UUID.randomUUID().toString()
    String firstName
    String lastName
    String userEmail
    String photoUri
    String username
    String password
    boolean enabled = false
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [loans: Loan, loanRequests:LoanRequest]

    Set<Authority> getAuthorities() {
        (UserAuthority.findAllByUser(this) as List<UserAuthority>)*.authority as Set<Authority>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
    }

    static mapping = {
	    password column: '`password`'
        uuid index: '`uuid`'
        photoUri sqlType: 'LONGTEXT'
    }



}
