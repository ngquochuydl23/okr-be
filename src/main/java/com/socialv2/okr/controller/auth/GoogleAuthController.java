package com.socialv2.okr.controller.auth;

import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class GoogleAuthController {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    @GetMapping("/login-redirect")
    public void loginRedirect(HttpServletResponse response) {
        String url = UriComponentsBuilder
                .fromUriString("https://accounts.google.com/o/oauth2/v2/auth")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .queryParam("scope", "openid profile email")
                .queryParam("prompt", "select_account")
                .build()
                .toUriString();
    }

//    @GetMapping("/callback")
//    public void googleCallback(@RequestParam("code") String code, HttpServletResponse response) throws IOException {
//
//        // 1. Đổi code lấy access_token + id_token
//        GoogleTokenResponse token = googleAuthService.exchangeCode(code);
//
//        // 2. Lấy thông tin user
//        GoogleUserInfo userInfo = googleAuthService.getUserInfo(token.getAccessToken());
//
//        // 3. Upsert vào database
//        User user = googleAuthService.upsertUser(userInfo);
//
//        // 4. Tạo JWT nội bộ
//        String jwt = googleAuthService.generateJwt(user);
//
//        // 5. Set cookie như FastAPI
//        Cookie cookie = new Cookie("access_token", jwt);
//        cookie.setHttpOnly(false);   // FastAPI để false
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(86400);
//        cookie.setSameSite("Lax");
//        response.addCookie(cookie);
//
//        // 6. Redirect FE
//        response.sendRedirect("https://your-frontend.com");
//    }
}
