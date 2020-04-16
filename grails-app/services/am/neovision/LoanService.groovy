package am.neovision

import grails.gorm.transactions.Transactional

import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Transactional
class LoanService {

    private EmailService emailService

    LoanService(EmailService emailService) {
        this.emailService = emailService
    }

    double calculateLoanMonthlyPaymentWithUniformPayments(int amount, double rate, int term){
        BigDecimal r = rate/(100*12)
        BigDecimal R=new BigDecimal(amount*r/(1-(1/Math.pow((1+r),term)))).setScale(2,RoundingMode.HALF_UP)
        return R.doubleValue()
    }

    Set<AmortizationSchedule> getLoanPaymentSchedule(int amount, double rate, int term, String startDate) {
        BigDecimal amountTmp  = amount
        AmortizationSchedule amortizationSchedule
        Set<AmortizationSchedule> amortizationSchedules = new TreeSet<>()
        BigDecimal totalInterest = 0
        LocalDateTime localDateTime = new Date(startDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        DateTimeFormatter formmat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        BigDecimal r = new BigDecimal(rate/(100*12)).setScale(3,RoundingMode.HALF_UP)
        BigDecimal R=new BigDecimal((amountTmp*r/(1-(1/Math.pow((1+r),term))))).setScale(2,RoundingMode.HALF_UP)
        for (int i=1;i<=term;i++){
            amortizationSchedule = new AmortizationSchedule()
            amortizationSchedule.paymentDate = formmat.format(localDateTime.plusMonths(i)).toString()
            amortizationSchedule.payment = R
            amortizationSchedule.interest = new BigDecimal(amountTmp*r).setScale(2,RoundingMode.HALF_UP)
            amortizationSchedule.principal = amortizationSchedule.payment-amortizationSchedule.interest
            totalInterest+=amortizationSchedule.interest
            amortizationSchedule.totalInterest=totalInterest
            amortizationSchedule.balance = (amountTmp-amortizationSchedule.principal)<0.5?0:amountTmp-amortizationSchedule.principal
            amortizationSchedule.status = AmortizationStatus.NOTPAYED
            amountTmp = amortizationSchedule.balance
            amortizationSchedules.add(amortizationSchedule)
        }
        return amortizationSchedules
    }

    LoanRequest createLoanRequest(LoanRequest loanRequestTmp,String created, String preferredPaymentDate) {
        LoanRequest loanRequest = new LoanRequest()
        loanRequest.status = LoanRequestStatus.REQUESTED
        loanRequest.user = User.findByUsername(loanRequestTmp.user.username)
        loanRequest.crated = new Date(created)
        loanRequest.loanType = loanRequestTmp.loanType
        loanRequest.preferredLoanAmount = loanRequestTmp.preferredLoanAmount
        loanRequest.preferredLoanInterestRate = loanRequestTmp.preferredLoanInterestRate
        loanRequest.preferredLoanTerm = loanRequestTmp.preferredLoanTerm
        loanRequest.preferredPaymentDate = new Date(preferredPaymentDate)
        loanRequest.save()
        return loanRequest
    }

    LoanRequest updateLoanRequest(LoanRequest loanRequestTmp,String created, String preferredPaymentDate) {
        LoanRequest loanRequest = LoanRequest.findById(loanRequestTmp.id)
        if (loanRequest.status == LoanRequestStatus.REQUESTED){
            loanRequest.status = LoanRequestStatus.APPROVED
        }
        loanRequest.user = User.findByUsername(loanRequestTmp.user.username)
        loanRequest.crated = new Date(created.replace('-','/'))
        loanRequest.loanType = loanRequestTmp.loanType
        loanRequest.preferredLoanAmount = loanRequestTmp.preferredLoanAmount
        loanRequest.preferredLoanInterestRate = loanRequestTmp.preferredLoanInterestRate
        loanRequest.preferredLoanTerm = loanRequestTmp.preferredLoanTerm
        loanRequest.preferredPaymentDate = new Date(preferredPaymentDate.replace('-','/'))
        loanRequest.save(false)
        switch (loanRequest.status){
            case LoanRequestStatus.APPROVED:
                emailService.sendNotificationAboutApprovingLoan(loanRequest)
                break
            case LoanRequestStatus.CANCELED:
                emailService.sendNotificationAboutCancelingLoan(loanRequest)
                break
        }
        return loanRequest
    }

    Set<LoanRequest> getLoanrequests(String username) {
        return User.findByUsername(username).loanRequests
    }

    Set<LoanRequest> getLoanRequestsWithStatusRequested() {
        Set<LoanRequest> loanRequests = LoanRequest.findAllByStatus(LoanRequestStatus.REQUESTED)

    }

    LoanRequest updateLoanRequestStatusByUser(LoanRequest loanRequestTmp) {
        LoanRequest loanRequest = LoanRequest.findById(loanRequestTmp.id)
        loanRequest.status = LoanRequestStatus.APPROYUSER
        loanRequest.save()
        return loanRequest
    }

    Set<LoanRequest> getLoanRequestsWithStatus(LoanRequestStatus loanRequestStatus) {
        Set<LoanRequest> loanRequests
        if (loanRequestStatus.equals(LoanRequestStatus.APPROVED)||loanRequestStatus.equals(LoanRequestStatus.APPROVEDBYUSER)){
            def criteria = LoanRequest.createCriteria()
            loanRequests = criteria.list {
               or {
                   eq("status",LoanRequestStatus.APPROVED)
                   eq("status",LoanRequestStatus.APPROVEDBYUSER)
               }
            }
        }else if (loanRequestStatus.equals(LoanRequestStatus.CANCELED)||loanRequestStatus.equals(LoanRequestStatus.CANCELEDBYUSER)){
            def criteria = LoanRequest.createCriteria()
            loanRequests = criteria.list {
                or {
                    eq("status",LoanRequestStatus.CANCELED)
                    eq("status",LoanRequestStatus.CANCELEDBYUSER)
                }
            }
        }else {
            loanRequests = LoanRequest.findAllByStatus(loanRequestStatus)
        }
        return loanRequests
    }
}
