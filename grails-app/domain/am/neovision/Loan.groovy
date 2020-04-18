package am.neovision





class Loan implements Serializable{

    private static final long serialVersionUID = 1

    transient springSecurityService

    String loanUUID = UUID.randomUUID().toString()
    BigDecimal loanAmount
    int loanTerm
    float loanInterestRate
    Date createDate = new Date()
    LoanType loanType
    static hasMany = [amortiazation: AmortizationSchedule]
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


    @Override
     String toString() {
        return "Loan{" +
                "id=" + id +
                ", version=" + version +
                ", user=" + user +
                ", springSecurityService=" + springSecurityService +
                ", loanUUID='" + loanUUID + '\'' +
                ", loanAmount=" + loanAmount +
                ", loanTerm=" + loanTerm +
                ", loanInterestRate=" + loanInterestRate +
                ", createDate=" + createDate +
                ", loanType=" + loanType +
                '}';
    }
}

