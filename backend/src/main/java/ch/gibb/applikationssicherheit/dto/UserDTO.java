package ch.gibb.applikationssicherheit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserDTO {

    private String username;

    private String password;

    private int enabled;
}
