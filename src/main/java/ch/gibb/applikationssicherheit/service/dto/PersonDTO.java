package ch.gibb.applikationssicherheit.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class PersonDTO implements Serializable {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private Set<PersonAuthorityDTO> personAuthorities;
}
