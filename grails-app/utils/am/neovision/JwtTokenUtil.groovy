package am.neovision

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value

import java.util.function.Function

class JwtTokenUtil {
/*    @Value("\${grails.plugin.springsecurity.rest.token.storage.jwt.secret}")
    private String secret;


    String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims.&getSubject)
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token)
        if (claims != null) {
            return claimsResolver.apply(claims)
        }
        return null
    }

    String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>()
        return doGenerateToken(claims, userDetails.getEmail())
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
        } catch (MalformedJwtException e) {
            return null
        }
    }

    Boolean validateToken(String token, User userDetails) {
        if (userDetails != null) {
            final String email = getEmailFromToken(token)
            return email.equals(userDetails.getEmail())
        }
        return false
    }*/
}
