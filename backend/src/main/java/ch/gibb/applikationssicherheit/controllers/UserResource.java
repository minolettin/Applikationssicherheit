package ch.gibb.applikationssicherheit.controllers;

import ch.gibb.applikationssicherheit.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserResource {

    @Autowired
    ResourceService resourceService;

    @GetMapping({"/executecommand"})
    public String executeCommand(@RequestParam(name = "sysopt") String command) {
        return resourceService.executeCommand(command);
    }

    @PostMapping({"/register"})
    public String register(@RequestParam String username, String password) {
        return resourceService.register(username, password);
    }

}
