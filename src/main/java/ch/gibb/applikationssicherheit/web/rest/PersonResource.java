package ch.gibb.applikationssicherheit.web.rest;

import ch.gibb.applikationssicherheit.domain.Person;
import ch.gibb.applikationssicherheit.service.JWTService;
import ch.gibb.applikationssicherheit.service.PersonService;
import ch.gibb.applikationssicherheit.service.dto.PersonDTO;
import ch.gibb.applikationssicherheit.web.rest.util.JwtResponse;
import ch.gibb.applikationssicherheit.web.rest.util.LoginForm;
import ch.gibb.applikationssicherheit.web.rest.util.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PersonResource {

    private final PersonService personService;

    private final JWTService jwtService;

    @PostMapping("/persons/sign-up")
    public ResponseEntity<PersonDTO> register(@Valid @RequestBody RegisterForm registerForm) {
        Person newPerson = new Person(registerForm.getUsername(), registerForm.getFirstName(), registerForm.getLastName(), registerForm.getPassword());
        return new ResponseEntity<>(personService.create(newPerson), HttpStatus.CREATED);
    }

    @PostMapping("/persons/sign-in")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(personService.login(loginForm));
    }

    @GetMapping("/persons/identify")
    public ResponseEntity<PersonDTO> identify(HttpServletRequest request) {
        String username = jwtService.getSubjectFromJwt(jwtService.getJwt(request));
        return ResponseEntity.ok(personService.getPersonDTOByUsername(username));
    }
}
