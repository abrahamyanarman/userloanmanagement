package am.neovision

import grails.gorm.transactions.Transactional

import java.math.RoundingMode

@Transactional
class LoanService {

    double calculateLoanMonthlyPaymentWithUniformPayments(int amount,double rate, int term){
        println(amount+"-"+rate+"-"+term)
        BigDecimal r = rate/(100*12)
        println(r)
        BigDecimal R=new BigDecimal(amount*r/(1-(1/Math.pow((1+r),term)))).setScale(2,RoundingMode.HALF_UP)
        println(R)
        return R.doubleValue()
    }
}
