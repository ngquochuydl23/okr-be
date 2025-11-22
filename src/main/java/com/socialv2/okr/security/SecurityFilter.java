package com.socialv2.okr.security;

import com.socialv2.okr.controller.errors.UnauthorizedAlertException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKeyConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.isBlank(request.getHeader("Authorization"))) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var user = validateToken(request);
            var authorities = Set.of(new SimpleGrantedAuthority(user.role()));
            var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"error\": \"" + ex.getMessage() + "\"}");
        }
    }

    private UserInfo validateToken(HttpServletRequest request) {
        try {
            var authHeader = request.getHeader("Authorization");
            if (StringUtils.isBlank(authHeader)) {
                return new UserInfo(null, null, null, null);
            }

            String token = authHeader.replace("Bearer ", "");
            if (StringUtils.isBlank(token)) {
                throw new UnauthorizedAlertException();
            }

            var secretKey = Keys.hmacShaKeyFor(secretKeyConfig.getBytes());
            var parser = Jwts.parser().verifyWith(secretKey).build();
            var payload = parser.parseSignedClaims(token).getPayload();

            return new UserInfo(
                    payload.get("userId", String.class),
                    payload.get("fullName", String.class),
                    payload.get("email", String.class),
                    payload.get("role", String.class)
            );
        } catch (ExpiredJwtException ex) {
            throw new UnauthorizedAlertException(null, "Token is expired", "tokenexpired");
        } catch (SignatureException ex) {
            throw new UnauthorizedAlertException(null, "Token is invalid", "tokeninvalid");
        }
    }
}
