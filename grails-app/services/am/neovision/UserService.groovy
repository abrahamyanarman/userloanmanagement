package am.neovision

import am.neovision.dto.ResponseDtoCommand
import am.neovision.dto.SignUpRequestCommand
import am.neovision.dto.UserInfo
import am.neovision.exception.CustomException
import am.neovision.maper.UserMapper
import grails.gorm.transactions.Transactional
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest
import javax.xml.bind.ValidationException

@Transactional
class UserService {
    private EmailService emailService

    private CustomAppRestAuthTokenJsonRenderer accessTokenJsonRenderer


    UserService(EmailService emailService,CustomAppRestAuthTokenJsonRenderer accessTokenJsonRenderer) {
        this.emailService = emailService
        this.accessTokenJsonRenderer = accessTokenJsonRenderer
    }

    User findById(id){
        User.findById(id)
    }


    UserInfo whoAmI(HttpServletRequest httpServletRequest) {
        def authHeader = null
        try {
            authHeader = httpServletRequest.getHeader("Authorization")
        } catch (Exception ex) {}
        if (authHeader != null) {
            def tokenParts = authHeader.replaceAll("Bearer ", "").split("\\.")
            def bytes = java.util.Base64.getDecoder().decode(tokenParts[1])
            def tokenPayload = new String(bytes, "UTF-8")

            String userName = new groovy.json.JsonSlurper().parseText(tokenPayload).sub
            return new UserMapper.FromUserToUserInfo().apply(User.findByUsername(userName))

        }else {
            throw new CustomException("Please signIn!", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    User findByEmail(String email) {
        return User.findByUserEmail(email)
    }

    void enableUser(String email) {
        User user = User.findByUserEmail(email)
        user.enabled = true
        user.save()
    }

    void changePassword(String email, String password) {
        User user = User.findByUserEmail(email)
        user.password = password
        user.save()
    }

    @Transactional
    ResponseDtoCommand createNewUser(SignUpRequestCommand signUpRequestCommand) {

        if(signUpRequestCommand.password != signUpRequestCommand.repassword) {
            return new ResponseDtoCommand( "Password and Re-Password not match.","errorCode:1004",HttpStatus.BAD_REQUEST)

        } else {
            try {
                def user = User.findByUsername(signUpRequestCommand.username)?: new User(
                        firstName:signUpRequestCommand.firstName,
                        lastName: signUpRequestCommand.lastName,
                        userEmail: signUpRequestCommand.email,
                        photoUri: signUpRequestCommand.photoUri,
                        username: signUpRequestCommand.username,
                        password: signUpRequestCommand.password).save()
                signUpRequestCommand.roles.each {role->
                    def userRole = Authority.get(role)
                    if(user && userRole) {
                        UserAuthority.create user, userRole


                    } else {
                        return new ResponseDtoCommand( "Something went wrong, please try again.","errorCode:1002",HttpStatus.BAD_REQUEST)

                    }
                }


                emailService.changePasswordForFirstLogin(User.findByUserEmail(signUpRequestCommand.email))

                return new ResponseDtoCommand( "You have registered successfully. Please Check your email to activate your account.",HttpStatus.OK)


            } catch (ValidationException e) {
                return new ResponseDtoCommand( "Something went wrong, please try again.","errorCode:1003",HttpStatus.BAD_REQUEST)
            }
        }
    }

    UserInfo userInfoById(long id) {
        return new UserMapper.FromUserToUserInfo().apply(User.findById(id))
    }

    @Transactional
    UserInfo updateUser(UserInfo userInfo) {
        User user = User.findById(userInfo.id)
        user.setUserEmail(userInfo.email.toLowerCase(Locale.ENGLISH))
        user.setUsername(userInfo.username.toLowerCase(Locale.ENGLISH))
        user.setFirstName(userInfo.firstName)
        user.setLastName(userInfo.lastName)
        user.setPhotoUri(userInfo.photoUri)
        user.setEnabled(userInfo.enabled)
        user.setAccountExpired(userInfo.accountExpired)
        user.setAccountLocked(userInfo.accountLocked)
        user.setPasswordExpired(userInfo.passwordExpired)
        user.save()
        return new UserMapper.FromUserToUserInfo().apply(User.findById(userInfo.id))
    }

 /*   void updateUserPhoto(long id, MultipartFile picture){
        User user = User.findById(id)
        user.photoUri = picture
        user.save()
    }

    byte[] getUserPhoto(long id) {
       User user =  User.findById(id)
        return user.photoUri
    }*/
}
