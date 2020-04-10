package am.neovision

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString(includeNames=true, includePackage=false)
class AmortizationShedule implements Serializable,Comparable<AmortizationShedule>{
    private static final long serialVersionUID = 145465465446

    static belongsTo = [loan:Loan]

    Date paymentDate
    BigDecimal payment
    BigDecimal principal
    BigDecimal interest
    BigDecimal totalInterest
    BigDecimal balance
    Date payedDate
    AmortizationStatus status = AmortizationStatus.NOTPAYED


    static constraints = {
        paymentDate nullable: false
        payment nullable: false
        principal nullable: false
        interest nullable: false
        totalInterest nullable: false
        balance nullable: false
        payedDate nullable: true
        status nullable: false
    }

    @Override
    int compareTo(AmortizationShedule o) {
        return paymentDate <=> o
    }
}
