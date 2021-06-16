package ch.gibb.applikationssicherheit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);

    public String executeCommand(String command) {
        // todo: replace with final code
        LOG.info("executed command: {}", command);
        return MessageFormat.format("executed command: {0}", command);
    }
}
