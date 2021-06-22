package ch.gibb.applikationssicherheit.web.rest.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;

import java.net.URI;

@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        Problem problem = entity.getBody();
        ProblemBuilder problemBuilder = Problem.builder()
                .withType(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString()))
                .withTitle(problem.getTitle())
                .withStatus(problem.getStatus());

        return new ResponseEntity<>(problemBuilder.build(), entity.getHeaders(), entity.getStatusCode());
    }
}

