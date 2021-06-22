package ch.gibb.applikationssicherheit.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDTO implements Serializable {

    private Long id;

    private String username;
}
