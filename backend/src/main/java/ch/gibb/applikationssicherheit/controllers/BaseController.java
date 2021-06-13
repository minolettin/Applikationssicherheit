package ch.gibb.applikationssicherheit.controllers;

import ch.gibb.applikationssicherheit.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class BaseController {

    @Autowired
    BaseService baseService;

    @GetMapping({"/executecommand"})
    public String executeCommand(@RequestParam(name = "sysopt") String command) {
        return baseService.executeCommand(command);
    }

    @PostMapping({"/register"})
    public String register(@RequestParam String username, String password) {
        return baseService.register(username, password);
    }

}
