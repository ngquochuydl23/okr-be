package com.socialv2.okr.controller.auth;

import com.socialv2.okr.dto.GoogleUserInfo;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.service.GoogleOAuthService;
import com.socialv2.okr.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class GoogleAuthController {

    private final GoogleOAuthService googleOAuthService;
    private final UserService userService;

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    @Value("${jwt.secret}")
    private String secretKeyConfig;

    @GetMapping("google/login-redirect")
    public void loginRedirect(HttpServletResponse response) throws IOException {
        String url = UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .queryParam("scope", "openid profile email")
                .queryParam("prompt", "select_account")
                .build()
                .toUriString();
        response.sendRedirect(url);
    }

    @GetMapping("google/callback")
    public ResponseEntity<Map<String, String>> callback(@RequestParam String code, HttpServletResponse response) throws IOException {
        GoogleUserInfo googleUserInfo = googleOAuthService.getGUserInfo(code);
        User user = userService.mergerFromGoogleUserInfo(googleUserInfo);
        String jwt = generateJwtToken(user);
//        Cookie cookie = new Cookie("access_token", jwt);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(false);
//        cookie.setPath("/");
//        cookie.setMaxAge(60 * 60 * 24);
//        response.addCookie(cookie);
        //response.sendRedirect("http://localhost:5000");
        return ResponseEntity.ok(Map.of(
                "message", "Login successful",
                "accessToken", jwt
        ));
    }

    private String generateJwtToken(User user) {
        return Jwts.builder()
                .subject((user.getEmail()))
                .claim("userId", user.getId())
                .claim("fullName", user.getFullName())
                .claim("role", user.getRole().getRoleName())
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 1000000))
                .signWith(Keys.hmacShaKeyFor(secretKeyConfig.getBytes()))
                .compact();
    }
}
