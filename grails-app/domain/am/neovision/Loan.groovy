package am.neovision

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString


@EqualsAndHashCode
@ToString(includeNames=true, includePackage=false)
class Loan implements Serializable{

    private static final long serialVersionUID = 1

    transient springSecurityService

    String loanUUID = UUID.randomUUID().toString()
    BigDecimal loanAmount
    int loanTerm
    float loanInterestRate
    Date createDate = new Date()
    LoanType loanType
    static belongsTo = [amortiazation: AmortizationSchedule]
    static hasOne = [user:User]



    static constraints = {
       loanAmount nullable: false
       loanType nullable: false
       createDate nullable: false

    }
    static mapping = {
        amortiazation lazy: true
        loanType sqlType: 'TEXT'
    }

}

