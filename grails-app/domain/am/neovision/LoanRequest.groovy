package am.neovision

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true, includePackage=false)
class LoanRequest  implements Serializable{
    private static final long serialVersionUID = 145444444

    static belongsTo = [user:User]
    BigDecimal preferredLoanAmount
    int preferredLoanTerm
    float preferredLoanInterestRate
    Date preferredPaymentDate
    LoanType loanType
    Date crated
    LoanRequestStatus status = LoanRequestStatus.REQUESTED

    static constraints = {
        preferredLoanAmount nullable: false
        preferredPaymentDate nullable: false
        loanType nullable: false
    }
}
