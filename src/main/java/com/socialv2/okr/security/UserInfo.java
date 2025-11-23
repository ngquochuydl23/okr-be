package com.socialv2.okr.security;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record UserInfo(
        @JsonProperty("userId") String id,
        @JsonProperty("fullName") String fullName,
        @JsonProperty("email") String email,
        @JsonProperty("role") String role
) implements Serializable { }
