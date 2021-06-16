package ch.gibb.applikationssicherheit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public String register(String username, String password) {
        LOG.info("registered user: {}", username);
        return MessageFormat.format("registered user: {0} with pw: {1}", username, password);
    }
}
