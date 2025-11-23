package com.socialv2.okr.service;

import com.socialv2.okr.dto.GoogleUserInfo;

public interface GoogleOAuthService {
    GoogleUserInfo getGUserInfo(String code);
}
