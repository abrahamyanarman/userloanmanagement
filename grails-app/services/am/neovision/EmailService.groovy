package am.neovision

import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async

import javax.mail.internet.MimeMessage

@Transactional
class EmailService {

    private JavaMailSender javaMailSender
    private EmailCodeService emailCodeService
    @Value("\${activateAccountUrl}")
    private String activateAccountUrl
    @Value("\${changePasswordUrl}")
    private String changePasswordUrl
    @Value("\${resetPasswordUrl}")
    private String resetPasswordUrl
    @Value("\${contactUsUrl}")
    private String contactUsUrl



    EmailService(JavaMailSender javaMailSender,  EmailCodeService emailCodeService) {
        this.javaMailSender = javaMailSender
        this.emailCodeService = emailCodeService
    }

    @Async
    def sendEmail(MimeMessage email ) {
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.auth","true")
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.starttls.enable","true")
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.starttls.required","true")
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.connectiontimeout","5000")
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.timeout","5000")
        (javaMailSender as JavaMailSenderImpl).session.properties.put("mail.smtp.writetimeout","5000")
        (javaMailSender as JavaMailSenderImpl).port = 587
        (javaMailSender as JavaMailSenderImpl).host = "smtp.gmail.com"
        (javaMailSender as JavaMailSenderImpl).username = "noreply.interma@gmail.com"
        (javaMailSender as JavaMailSenderImpl).password = "a12345678."
        javaMailSender.send(email)
    }

    String sendAccountActivationMail(User user){
        def emailTemplate = new EmailTemplate("activateAccount.html")
        String activateUrl = "$activateAccountUrl${emailCodeService.generateCode(user.userEmail)}"
        Map<String,String> replacements = new HashMap<String,String>()
        replacements["{{fullName}}"] = "${user.firstName} ${user.lastName}"
        replacements["{{contactUsUrl}}"] = contactUsUrl
        replacements["{{activateUrl}}"] = activateUrl
        String messageText = emailTemplate.getTemplate(replacements)

        def mailMessage =javaMailSender.createMimeMessage()
        def mimeMessageHelper = new MimeMessageHelper(mailMessage, true)
        mimeMessageHelper.setTo(user.userEmail)
        mimeMessageHelper.setSubject("Complete Registration!")
        mimeMessageHelper.setText(messageText,true)
        sendEmail(mailMessage)

        return "Check your email for activating your account"
    }

    String sendResetPasswordMail(User user){


        EmailTemplate emailTemplate = new EmailTemplate("resetPassword.html")
        String resetPasswordUrl = "$resetPasswordUrl${emailCodeService.generateCode(user.userEmail)}"
        Map<String,String> replacements = new HashMap<String,String>()
        replacements["{{fullName}}"] = "${user.firstName} ${user.lastName}"
        replacements["{{contactUsUrl}}"] = contactUsUrl
        replacements["{{resetPasswordUrl}}"] = resetPasswordUrl
        String messageText = emailTemplate.getTemplate(replacements)

        def mailMessage = javaMailSender.createMimeMessage()
        def mimeMessageHelper = new MimeMessageHelper(mailMessage, true)
        mimeMessageHelper.setTo(user.userEmail)
        mimeMessageHelper.setSubject("Reset Password")
        mimeMessageHelper.setText(messageText,true)
        sendEmail(mailMessage)

        return "Check your email for next steps!"
    }

    String changePasswordForFirstLogin(User user){
        EmailTemplate emailTemplate = new EmailTemplate("activateAccount.html")
        String activateUrl = "$activateAccountUrl${emailCodeService.generateCode(user.userEmail)}"
        Map<String,String> replacements = new HashMap<String,String>()
        replacements["{{fullName}}"] = "${user.firstName} ${user.lastName}"
        replacements["{{contactUsUrl}}"] = contactUsUrl
        replacements["{{activateAccountUrl}}"] = activateUrl
        String messageText = emailTemplate.getTemplate(replacements)
        def mailMessage =javaMailSender.createMimeMessage()
        def mimeMessageHelper = new MimeMessageHelper(mailMessage, true)

        mimeMessageHelper.setTo(user.userEmail)
        mimeMessageHelper
        mimeMessageHelper.setSubject("Activate Account")
        mimeMessageHelper.setText(messageText,true)
        sendEmail(mailMessage)

        return"Check your email for activating your account"
    }

    Boolean confirmEmail(String code) {
        EmailCodes emailCodesResponse = emailCodeService.getByCode(code.toLong())
        if (emailCodesResponse!=null){
            String email = emailCodesResponse.getEmail()
            if (!email.isEmpty()){
                return true
            }
        }
        return false
    }

    Boolean checkEmailCodeExist(String emailcode) {
        return emailCodeService.chackEmailCodeExist(emailcode)
    }

    String changePasswordByEmailCode(String emailcode,String password){
        String email = emailCodeService.getByCode(emailcode.toLong()).getEmail()
        //userService.changePassword(email,password)
        return "Password successfully changed!"
    }

    long generateCode(String email) {
        return emailCodeService.generateCode(email)
    }

    void sendNotificationAboutApprovingLoan(LoanRequest loanRequest) {
        EmailTemplate emailTemplate = new EmailTemplate("emailNotificationAboutAprrovingLoan.html")
        Map<String,String> replacements = new HashMap<String,String>()
        replacements["{{contactUsUrl}}"] = contactUsUrl
        replacements["{{loanId}}"] = loanRequest.id.toString()
        String messageText = emailTemplate.getTemplate(replacements)
        def mailMessage =javaMailSender.createMimeMessage()
        def mimeMessageHelper = new MimeMessageHelper(mailMessage, true)

        mimeMessageHelper.setTo(loanRequest.user.userEmail)
        mimeMessageHelper
        mimeMessageHelper.setSubject("Info About Loan")
        mimeMessageHelper.setText(messageText,true)
        sendEmail(mailMessage)
    }

    void sendNotificationAboutCancelingLoan(LoanRequest loanRequest) {

    }
}
