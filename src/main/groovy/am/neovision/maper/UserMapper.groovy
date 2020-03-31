package am.neovision.maper

import am.neovision.User
import am.neovision.dto.UserInfoResponseCommand

import java.util.function.Function

class UserMapper implements Function<User, UserInfoResponseCommand> {
    @Override
    UserInfoResponseCommand apply(User user) {
        final UserInfoResponseCommand userInfo = new UserInfoResponseCommand()
        userInfo.setEmail(user.userEmail.toLowerCase(Locale.ENGLISH))
        userInfo.setUsername(user.username.toLowerCase(Locale.ENGLISH))
        userInfo.setFirstName(user.firstName)
        userInfo.setLastName(user.lastName)
        userInfo.setPhotoUri(user.photoUri)
        userInfo.setAuthorities(user.getAuthorities())
        userInfo.setEnabled(user.enabled)
        userInfo.setAccountExpired(user.accountExpired)
        userInfo.setAccountLocked(user.accountLocked)
        userInfo.setPasswordExpired(user.passwordExpired)
        return userInfo
    }

}
