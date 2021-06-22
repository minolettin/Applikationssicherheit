package ch.gibb.applikationssicherheit.web.rest.util;

import ch.gibb.applikationssicherheit.service.dto.PersonDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String token;

    private PersonDTO personDTO;
}
