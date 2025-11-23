package com.socialv2.okr.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.socialv2.okr.dto.GoogleUserInfo;
import com.socialv2.okr.service.GoogleOAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GoogleOAuthServiceImpl implements GoogleOAuthService {

    @Value("${google.client-id}")
    private String clientId;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.redirect-uri}")
    private String redirectUri;

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    public GoogleUserInfo getGUserInfo(String code) {
        try {
            JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    jsonFactory,
                    clientId,
                    clientSecret,
                    code,
                    redirectUri
            ).execute();

            GoogleIdToken idToken = tokenResponse.parseIdToken();
            GoogleIdToken.Payload payload = idToken.getPayload();

            GoogleUserInfo user = new GoogleUserInfo(
                    payload.getEmail(),
                    Optional.ofNullable(payload.get("picture")).map(Object::toString).orElse(null),
                    Optional.ofNullable(payload.get("name")).map(Object::toString).orElse(null),
                    Optional.ofNullable(payload.get("given_name")).map(Object::toString).orElse(null),
                    Optional.ofNullable(payload.get("family_name")).map(Object::toString).orElse(null)
            );
            return user;
        } catch (GeneralSecurityException | IOException ex) {
            return null;
        }
    }
}
