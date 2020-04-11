package am.neovision

import grails.gorm.transactions.Transactional

import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Transactional
class LoanService {

    double calculateLoanMonthlyPaymentWithUniformPayments(int amount,double rate, int term){
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
}
