package br.com.mekki_auth.config;

import br.com.mekki_auth.to.UserTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TokenAuthenticator {
    static final long EXPIRATION_TIME = 860_000_000;
    static final String SECRET = "MySecret";
    public static final String HEADER_STRING = "Token";

    private static Logger log = LogManager.getLogger(TokenAuthenticator.class);

    public static String generateToken(UserTO user) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", user.getId());
            claims.put("school", user.getSchool());
            claims.put("roles", String.join(",", user.getRoles()));

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
        } catch (Exception e) {
            log.error("Error generating token for user: " + user.getUsername(), e);
            throw new RuntimeException("Failed to generate token", e);
        }
    }

    public static UserTO validateAndParseToken(String token) {
        try {
            Claims claims = getBody(token);
            if (claims == null) {
                return null;
            }

            UserTO user = new UserTO();
            user.setUsername(claims.get("username", String.class));
            user.setEmail(claims.getSubject());
            user.setSchool(claims.get("school", String.class));

            String rolesString = claims.get("roles", String.class);
            List<String> roles = rolesString != null ? List.of(rolesString.split(",")) : List.of();
            user.setRoles(roles);

            // Check expiration
            if (claims.getExpiration().before(new Date())) {
                log.warn("Token expired for user: " + user.getUsername());
                return null;
            }

            return user;
        } catch (Exception e) {
            log.error("Error parsing token", e);
            return null;
        }
    }

    private static Claims getBody(String token) {
        if (token == null || token.trim().isEmpty()) {
            return null;
        }
        try {
            JwtParser parser = Jwts.parser()
                    .setSigningKey(SECRET);
            return parser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("Exception in token parsing: ", e);
            return null;
        }
    }
}

