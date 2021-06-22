package ch.gibb.applikationssicherheit.web.rest.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class LoginForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
