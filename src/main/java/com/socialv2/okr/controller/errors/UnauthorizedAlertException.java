package com.socialv2.okr.controller.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class UnauthorizedAlertException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;
    private final String errorKey;

    public UnauthorizedAlertException() {
        super(ErrorConstants.DEFAULT_TYPE, "Unauthorized");
        this.errorKey = null;
    }

    public UnauthorizedAlertException(URI type, String defaultMessage, String errorKey) {
        super(type, defaultMessage, Status.UNAUTHORIZED, null, null, null, getAlertParameter(errorKey));
        this.errorKey = errorKey;
    }

    private static Map<String, Object> getAlertParameter(String errorKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", "error." + errorKey);
        return parameters;
    }

    public String getErrorKey() {
        return errorKey;
    }
}
