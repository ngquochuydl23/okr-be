package com.socialv2.okr.controller;

import com.socialv2.okr.dto.user.UserDTO;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.security.SecurityUtils;
import com.socialv2.okr.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/users/me")
    public ResponseEntity<String> updateMyProfile() {
        String userId = SecurityUtils.getCurrentLoggedInUserId().orElse(null);
        throw new NotImplementedException();
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("me")
    public ResponseEntity<UserDTO> getMyProfile() {
        String userId = SecurityUtils.getCurrentLoggedInUserId().orElse(null);
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
