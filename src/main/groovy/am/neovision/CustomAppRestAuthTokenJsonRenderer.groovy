package am.neovision

import grails.plugin.springsecurity.rest.token.AccessToken
import grails.plugin.springsecurity.rest.token.rendering.AccessTokenJsonRenderer
import groovy.json.JsonBuilder



class CustomAppRestAuthTokenJsonRenderer implements AccessTokenJsonRenderer {
    @Override
    String generateJson(AccessToken accessToken) {
        Map response = [
                id           : (accessToken.principal as MyUserDetails).id,
                uuid           : (accessToken.principal as MyUserDetails).uuid,
                username     : accessToken.principal.username,
                access_token : accessToken.accessToken,
                token_type   : "Bearer",
                expiration   : accessToken.expiration,
                refresh_token: accessToken.refreshToken,
                roles        : accessToken.authorities.collect {  role -> role.authority }
        ]

        return new JsonBuilder( response ).toPrettyString()
    }
}
