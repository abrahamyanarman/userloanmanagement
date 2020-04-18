package am.neovision




class AmortizationSchedule implements Serializable,Comparable<AmortizationSchedule>{
    private static final long serialVersionUID = 145465465446

    static belongsTo = [loan:Loan]

    String paymentDate
    BigDecimal payment
    BigDecimal principal
    BigDecimal interest
    BigDecimal totalInterest
    BigDecimal balance
    String payedDate
    AmortizationStatus status = AmortizationStatus.NOTPAYED

    AmortizationSchedule() {
    }

    AmortizationSchedule(id, loan, String paymentDate, BigDecimal payment, BigDecimal principal, BigDecimal interest, BigDecimal totalInterest, BigDecimal balance, String payedDate, AmortizationStatus status) {
        this.id = id
        this.loan = loan
        this.paymentDate = paymentDate
        this.payment = payment
        this.principal = principal
        this.interest = interest
        this.totalInterest = totalInterest
        this.balance = balance
        this.payedDate = payedDate
        this.status = status
    }
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
    int compareTo(AmortizationSchedule o) {
        return paymentDate <=> o.paymentDate
    }

    @Override
     String toString() {
        return "AmortizationSchedule{" +
                "id=" + id +
                ", version=" + version +
                ", loan=" + loan +
                ", paymentDate='" + paymentDate + '\'' +
                ", payment=" + payment +
                ", principal=" + principal +
                ", interest=" + interest +
                ", totalInterest=" + totalInterest +
                ", balance=" + balance +
                ", payedDate='" + payedDate + '\'' +
                ", status=" + status +
                '}';
    }
}
