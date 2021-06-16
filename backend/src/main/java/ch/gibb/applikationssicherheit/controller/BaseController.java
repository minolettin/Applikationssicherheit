package ch.gibb.applikationssicherheit.controller;

import ch.gibb.applikationssicherheit.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class BaseController {

    private final BaseService baseService;

    @GetMapping({"/executecommand"})
    public String executeCommand(@RequestParam(name = "sysopt") String command) {
        return baseService.executeCommand(command);
    }
}
