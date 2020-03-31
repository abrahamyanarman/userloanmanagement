import am.neovision.CustomAppRestAuthTokenJsonRenderer
import am.neovision.MyUserDetailsService
import am.neovision.UserPasswordEncoderListener
import org.springframework.mail.javamail.JavaMailSenderImpl

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    javaMailSender(JavaMailSenderImpl)
    accessTokenJsonRenderer(CustomAppRestAuthTokenJsonRenderer)
    userDetailsService(MyUserDetailsService)
}
