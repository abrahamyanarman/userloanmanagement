package am.neovision

import am.neovision.exception.CustomException
import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus

@Transactional
class EmailCodeService {

    EmailCodes getByCode(Long code) {
        EmailCodes emailCode = EmailCodes.findByCode(code)
        if (emailCode==null)
            throw new CustomException("Email Code not found", HttpStatus.BAD_REQUEST)
        emailCode.delete()
        return emailCode
    }

    Long generateCode(String email){
        Long code = (Math.random()*Long.MAX_VALUE).toLong()
        EmailCodes emailCode
        if (!(EmailCodes.countByEmail(email))){
            emailCode = new EmailCodes(email,code)
        }else{
            emailCode = EmailCodes.findByEmail(email)
            emailCode.setEmail(email)
            emailCode.setCode(code)
        }

        emailCode.save()
        return code
    }

    EmailCodes getByEmail(String email){
        EmailCodes emailCode = EmailCodes.findByEmail(email)
        if (emailCode==null)
            throw new CustomException("Email Code not found!",HttpStatus.BAD_REQUEST)
        emailCode.delete()
        return emailCode
    }

    Boolean chackEmailCodeExist(String emailcode)  {
        return EmailCodes.exists(emailcode.toLong())
    }
}
