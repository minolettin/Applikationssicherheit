package ch.gibb.applikationssicherheit.configuration;

import ch.gibb.applikationssicherheit.service.JWTService;
import ch.gibb.applikationssicherheit.service.PersonService;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(JWTAuthFilter.class);

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PersonService personService;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtService.getJwt(httpServletRequest);
            if (jwt != null && jwtService.validateJwt(jwt)) {
                EmailValidator emailValidator = EmailValidator.getInstance();
                String jwtSubject = jwtService.getSubjectFromJwt(jwt);
                Object principal = emailValidator.isValid(jwtSubject) ? personService.loadUserByUsername(jwtSubject) : jwtSubject;
                Authentication authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            LOGGER.error("Can NOT set user authentication -> Message: {}", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
