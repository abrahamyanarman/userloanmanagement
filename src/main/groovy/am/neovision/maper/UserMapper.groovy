package am.neovision.maper

import am.neovision.User
import am.neovision.dto.UserInfo

import java.util.function.Function

class UserMapper {


     class FromUserToUserInfo implements Function<User,UserInfo>{
        @Override
        UserInfo apply(User user) {
             UserInfo userInfo = new UserInfo()
            userInfo.setId(user.id)
            userInfo.setUuid(user.uuid)
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

     class FromUserInfoToUser implements Function<UserInfo,User>{
        @Override
        User apply(UserInfo userInfo) {
             User user = new User()
            user.setId(userInfo.id)
            user.setUuid(userInfo.uuid)
            user.setUserEmail(userInfo.email.toLowerCase(Locale.ENGLISH))
            user.setUsername(userInfo.username.toLowerCase(Locale.ENGLISH))
            user.setFirstName(userInfo.firstName)
            user.setLastName(userInfo.lastName)
            user.setPhotoUri(userInfo.photoUri)
            user.setEnabled(userInfo.enabled)
            user.setAccountExpired(userInfo.accountExpired)
            user.setAccountLocked(userInfo.accountLocked)
            user.setPasswordExpired(userInfo.passwordExpired)
            return user
        }
    }
}
