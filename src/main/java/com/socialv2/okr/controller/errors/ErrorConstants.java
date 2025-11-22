package com.socialv2.okr.controller.errors;

import java.net.URI;

public final class ErrorConstants {
    public static final String PROBLEM_BASE_URL = "https://localhost/problem";
    public static final String DEFAULT_TYPE_URL = PROBLEM_BASE_URL + "/problem-with-message";
    public static final URI DEFAULT_TYPE = URI.create(DEFAULT_TYPE_URL);
}
