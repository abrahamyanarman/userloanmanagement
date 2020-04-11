package userloanmanagement

import am.neovision.LoanRequest
import am.neovision.LoanService
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.grails.web.json.JSONObject
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

class LoanController {
    static responseFormats = ['json']
    private LoanService loanService

    LoanController(LoanService loanService) {
        this.loanService = loanService
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def getLoanMonthlyPaymentWithUniformPayments(@RequestParam int loanAmount,@RequestParam double loanInterest,@RequestParam int loanTerm){
        respond new JSONObject("{monthlyPayment:"+loanService.calculateLoanMonthlyPaymentWithUniformPayments(loanAmount,loanInterest,loanTerm)+"}")
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def getLoanPaymentSchedule(@RequestParam int loanAmount,@RequestParam double loanInterest,@RequestParam int loanTerm, @RequestParam String startDate){
        respond loanService.getLoanPaymentSchedule(loanAmount,loanInterest,loanTerm,startDate)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def createLoanRequest(@RequestBody LoanRequest loanRequest,@RequestParam String created,@RequestParam String preferredPaymentDate){
        respond loanService.createLoanRequest(loanRequest,created,preferredPaymentDate)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getLoanrequests(@RequestParam String username){
        respond loanService.getLoanrequests(username)
    }


}
