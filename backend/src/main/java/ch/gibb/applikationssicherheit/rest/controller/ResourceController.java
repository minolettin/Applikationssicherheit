package ch.gibb.applikationssicherheit.rest.controller;

import ch.gibb.applikationssicherheit.rest.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

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
