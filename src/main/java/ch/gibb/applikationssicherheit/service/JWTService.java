package ch.gibb.applikationssicherheit.service;

import ch.gibb.applikationssicherheit.domain.Person;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class JWTService {

    private final Logger LOGGER = LoggerFactory.getLogger(JWTService.class);

    @Value("${applikationssicherheit.jwtExpirationMillis}")
    private int jwtExpirationMillis;

    @Value("${applikationssicherheit.jwtSigningKey}")
    private String jwtSigningKey;

    public String generateJwt(Authentication authentication) {
        Person person = (Person) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(person.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMillis))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey)
                .compact();
    }

    public boolean validateJwt(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    public String getSubjectFromJwt(String jwt) {
        if (jwt != null) {
            return Jwts.parser()
                    .setSigningKey(jwtSigningKey)
                    .parseClaimsJws(jwt)
                    .getBody().getSubject();
        }
        return null;
    }

    public String getJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}

