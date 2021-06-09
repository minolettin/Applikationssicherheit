package ch.gibb.applikationssicherheit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ResourceService {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceService.class);


    public String executeCommand(String command) {
        // todo: replace with final code
        LOG.info("executed command: {}", command);
        return MessageFormat.format("executed command: {0}", command);
    }

    public String register(String username, String password) {
        // todo: replace with final code
        LOG.info("registered user: {}", username);
        return MessageFormat.format("registered user: {0} with pw: {1}", username, password);
    }
}
