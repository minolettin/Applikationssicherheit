package ch.gibb.applikationssicherheit.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonAuthorityDTO implements Serializable {

    private Long id;

    private Long personId;

    private Long authorityId;

    private String authorityName;
}
