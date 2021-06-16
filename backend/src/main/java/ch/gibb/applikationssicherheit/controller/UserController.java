package ch.gibb.applikationssicherheit.controller;

import ch.gibb.applikationssicherheit.dto.UserDTO;
import ch.gibb.applikationssicherheit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;




    @PostMapping({"/register"})
    public String register(@RequestParam String username, String password) {
        return userService.register(username, password);
    }
}
