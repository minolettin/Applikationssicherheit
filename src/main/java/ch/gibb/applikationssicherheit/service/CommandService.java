package ch.gibb.applikationssicherheit.service;

import ch.gibb.applikationssicherheit.service.dto.CommandDTO;
import ch.gibb.applikationssicherheit.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class CommandService {

    private final Logger logger = LoggerFactory.getLogger(CommandService.class);

    private final String[] allowedOptions = new String[]{"al"};

    public CommandDTO executeCommand(String[] parameters) throws BadRequestAlertException {
        final StringBuilder resultBuilder = new StringBuilder();

        if (!areArgumentsValid(parameters)) {
            throw new BadRequestAlertException("PARAMETERS ARE NOT VALID");
        }
        final String buildOptions = buildOptions(parameters);
        try {
            Runtime r = Runtime.getRuntime();
            Process p;
            if (parameters == null) {
                logger.info("Executing command 'ls'");
                p = r.exec("ls");
            } else {
                logger.info("Executing command 'ls " + buildOptions + "'");
                p = r.exec("ls " + buildOptions);
            }
            p.waitFor();
            BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = b.readLine()) != null) {
                resultBuilder.append(line);
            }

            b.close();

        } catch ( InterruptedException | IOException e) {
            throw new BadRequestAlertException(e.getMessage());
        }

        return new CommandDTO(resultBuilder.toString());
    }

    private String buildOptions(String[] options) {
        if (options == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String option : options) {
            builder.append(" -").append(option);
        }
        return builder.toString();
    }

    private boolean areArgumentsValid(String[] options) {
        for (String option : options) {
            if (!Arrays.asList(this.allowedOptions).contains(option)) {
                return false;
            }
        }
        return true;
    }
}

