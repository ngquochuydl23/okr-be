package com.socialv2.okr.service;

import com.socialv2.okr.dto.GoogleUserInfo;
import com.socialv2.okr.dto.user.UserDTO;
import com.socialv2.okr.entities.users.User;

import java.util.List;

public interface UserService {
    UserDTO getUserById(String userId);

    List<UserDTO> getAllUsers();

    List<User> getUserByIds(List<String> ids);

    User mergerFromGoogleUserInfo(GoogleUserInfo gUser);
}
