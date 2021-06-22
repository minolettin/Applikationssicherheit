package ch.gibb.applikationssicherheit.service;

import ch.gibb.applikationssicherheit.domain.Person;
import ch.gibb.applikationssicherheit.repository.PersonRepository;
import ch.gibb.applikationssicherheit.service.dto.PersonDTO;
import ch.gibb.applikationssicherheit.service.mapper.PersonMapper;
import ch.gibb.applikationssicherheit.web.rest.errors.BadRequestAlertException;
import ch.gibb.applikationssicherheit.web.rest.util.JwtResponse;
import ch.gibb.applikationssicherheit.web.rest.util.LoginForm;
import lombok.RequiredArgsConstructor;
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

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public PersonDTO findById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new BadRequestAlertException("USER COULD NOT BE FOUND");
        }
        return personMapper.toDto(person.get());
    }

    public PersonDTO create(Person person) {
        if (personRepository.existsByUsername(person.getUsername())) {
            throw new BadRequestAlertException("USERNAME IS ALREADY USED");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personMapper.toDto(personRepository.save(person));
    }

    public JwtResponse login(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwt(authentication);
        PersonDTO personDTO = personMapper.toDto((Person) authentication.getPrincipal());
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
