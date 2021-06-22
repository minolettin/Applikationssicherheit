package ch.gibb.applikationssicherheit.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestAlertException extends AbstractThrowableProblem {

    public BadRequestAlertException(String errorMessage) {
        super(null, errorMessage, Status.BAD_REQUEST);
    }
}
