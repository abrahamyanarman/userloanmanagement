package am.neovision

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='email')
@ToString(includes='email', includeNames=true, includePackage=false)
class EmailCodes implements Serializable  {

    private static final String serialVersionUID = -97598793L

    transient springSecurityService

    EmailCodes(String email, Long code) {
        this.email = email
        this.code = code
    }
    String email
    Long code
    static constraints = {
        email nullable:false,  blank: false, unique: true
        code nullable: false, blank: false
    }

    static mapping = {
        email column: '`email`'
        code column: '`code`'

    }
    static transients = ['springSecurityService']

}
