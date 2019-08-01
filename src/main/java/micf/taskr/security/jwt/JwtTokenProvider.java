package micf.taskr.security.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import micf.taskr.domain.user.User;

import static micf.taskr.config.SecurityConstraints.EXPIRATION_TIME;
import static micf.taskr.config.SecurityConstraints.SECRET_KEY;

@Component
public class JwtTokenProvider {

    // Generate Token
    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("email", user.getEmail());
        claims.put("name", user.getFirstName() + " " + user.getLastName());
        claims.put("roles", user.getRoles());

        return Jwts.builder()
                    .setSubject(userId)
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                    .compact();
    }
    // Validate the token

    // Get user ID from token
}