package ch.gibb.applikationssicherheit.web.rest.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class RegisterForm {

    @NotEmpty
    @Size(max = 50)
    private String username;

    @NotEmpty
    @Size(max = 50)
    private String firstName;

    @NotEmpty
    @Size(max = 50)
    private String lastName;

    @NotEmpty
    @Size(min = 8, max = 50, message = "INVALID PASSWORD")
    private String password;
}
