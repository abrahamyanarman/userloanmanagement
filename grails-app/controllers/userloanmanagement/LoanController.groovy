package userloanmanagement

import am.neovision.LoanRequest
import am.neovision.LoanRequestStatus
import am.neovision.LoanService
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import org.grails.web.json.JSONObject
import org.springframework.web.bind.annotation.PathVariable
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
    def createLoanRequest(@RequestBody LoanRequest loanRequest,@RequestParam String created,@RequestParam String preferredPaymentDate,@RequestParam String preferredStartDate){
        respond loanService.createLoanRequest(loanRequest,created,preferredPaymentDate,preferredStartDate)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getLoanrequests(@RequestParam String username){
        respond loanService.getLoanrequests(username)
    }

    @Secured(['ROLE_ADMIN'])
    def getLoanRequestsWithStatusRequested(){
        respond loanService.getLoanRequestsWithStatusRequested()
    }

    @Secured(['ROLE_ADMIN'])
    def updateLoanRequest(@RequestBody LoanRequest loanRequest,@RequestParam String created,@RequestParam String preferredPaymentDate){
        respond loanService.updateLoanRequest(loanRequest,created,preferredPaymentDate)
    }

    @Secured(['ROLE_USER'])
    def updateLoanRequestStatusByUser(@RequestBody LoanRequest loanRequest){
        respond loanService.updateLoanRequestStatusByUser(loanRequest)
    }

    @Secured(['ROLE_ADMIN'])
    def getLoanRequestsWithStatus(@RequestParam String status){
        respond loanService.getLoanRequestsWithStatus(LoanRequestStatus.valueOf(status))
    }

    def createLoan(@RequestBody LoanRequest loanRequest,@RequestParam String preferredStartDate,@RequestParam String preferredPaymentDate){
        respond loanService.createLoan(loanRequest,preferredStartDate,preferredPaymentDate)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getLoans(@RequestParam String username){
        respond loanService.getLoans(username)
    }

    @Secured(['ROLE_ADMIN','ROLE_USER'])
    def getLoanSchedule(@PathVariable long id){
        respond loanService.getLoanSchedule(id)
    }
}
