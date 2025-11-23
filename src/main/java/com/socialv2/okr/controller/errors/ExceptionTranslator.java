package com.socialv2.okr.controller.errors;

import com.socialv2.okr.common.Constants;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationProblem;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {
    private static final String MESSAGE_KEY = "message";;
    private static final String PATH_KEY = "path";
    private static final String VIOLATIONS_KEY = "violations";
    private static final String ALERT_TYPE_KEY = "alertType";

    private MessageSource messageSource;

    @Autowired
    public ExceptionTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public ResponseEntity<Problem> process(ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return null;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }
        ProblemBuilder builder = Problem.builder()
                .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? ErrorConstants.DEFAULT_TYPE : problem.getType())
                .withStatus(problem.getStatus())
                .withTitle(problem.getTitle())
                .with(PATH_KEY, Objects.requireNonNull(request.getNativeRequest(HttpServletRequest.class)).getRequestURI());

        if (problem instanceof ConstraintViolationProblem) {
            builder.with("errorKey", "constraintviolation")
                    .with(VIOLATIONS_KEY, ((ConstraintViolationProblem) problem).getViolations())
                    .with(ALERT_TYPE_KEY, AlertType.WARNING);

            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                Locale locale = request.getLocale();
                String errorMessage = messageSource.getMessage(Constants.ERROR_MESSAGE_KEY.INVALID_ARGUMENT.getKey(), null, locale);
                builder.with(MESSAGE_KEY, errorMessage)
                        .withTitle(errorMessage);
            }
        } else {
            builder.withCause(((DefaultProblem) problem).getCause())
                    .withDetail(problem.getDetail())
                    .withInstance(problem.getInstance())
                    .with(ALERT_TYPE_KEY, AlertType.WARNING);
            problem.getParameters().forEach(builder::with);

            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                Locale locale = request.getLocale();
                String errorMessage = messageSource.getMessage(Constants.ERROR_MESSAGE_KEY.ERROR_GENERAL.getKey(), null, locale);

                builder.with(MESSAGE_KEY, errorMessage);
            }
        }
        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

    @ExceptionHandler
    public void handleIOException(IOException ex, NativeWebRequest request) {
        log.warn(ex.getMessage());
    }
}
