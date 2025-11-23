package com.socialv2.okr.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class SecurityUtils {
    public static Optional<String> getCurrentLoggedInUserId() {
        var securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractUserId(securityContext.getAuthentication()));
    }

    private static String extractUserId(Authentication authentication) {
        if (Objects.nonNull(authentication) && authentication.getPrincipal() instanceof UserInfo user) {
            return user.id();
        }
        return "admin";
    }
}
