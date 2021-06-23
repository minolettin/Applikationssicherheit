package ch.gibb.applikationssicherheit.web.rest;

import ch.gibb.applikationssicherheit.service.CommandService;
import ch.gibb.applikationssicherheit.service.dto.CommandDTO;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
@RequestMapping("/api")
public class CommandResource {

    private final CommandService commandService;

    @GetMapping(value = "/commands", produces = "application/json", params = {"parameters"})
    public CommandDTO executeCommand(@RequestParam(name = "parameters") String[] parameters) {
        return commandService.executeCommand(parameters);
    }
}

