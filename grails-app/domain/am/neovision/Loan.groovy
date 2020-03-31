package am.neovision

import grails.rest.Resource

@Resource(uri='/api/loan')
class Loan implements Serializable{

    private static final long serialVersionUID = 1

    transient springSecurityService

    String loanUUID
    BigDecimal amount
    Integer term
    Date createDate = new Date()



    static constraints = {
    }
}
