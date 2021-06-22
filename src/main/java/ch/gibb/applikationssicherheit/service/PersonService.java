package ch.gibb.applikationssicherheit.service;

import ch.gibb.applikationssicherheit.domain.Person;
import ch.gibb.applikationssicherheit.repository.PersonRepository;
import ch.gibb.applikationssicherheit.service.dto.PersonDTO;
import ch.gibb.applikationssicherheit.service.mapper.PersonMapper;
import ch.gibb.applikationssicherheit.web.rest.PersonResource;
import ch.gibb.applikationssicherheit.web.rest.errors.BadRequestAlertException;
import ch.gibb.applikationssicherheit.web.rest.util.JwtResponse;
import ch.gibb.applikationssicherheit.web.rest.util.LoginForm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public PersonDTO create(Person person) {
        if (personRepository.existsByUsername(person.getUsername())) {
            logger.error("Registering didn't work because username " + person.getUsername() + " is already taken!");
            throw new BadRequestAlertException("USERNAME IS ALREADY USED");
        }
        logger.info("Person has registered with username: " + person.getUsername());
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personMapper.toDto(personRepository.save(person));
    }

    public JwtResponse login(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwt(authentication);
        PersonDTO personDTO = personMapper.toDto((Person) authentication.getPrincipal());
        logger.info("Person has logged in with username: " + loginForm.getUsername());
        return new JwtResponse(jwt, personDTO);
    }

    public PersonDTO getPersonDTOByUsername(String username) {
        return personMapper.toDto((Person) loadUserByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("USER DOES NOT EXIST: " + username));
    }
}
