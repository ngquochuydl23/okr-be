package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.GoogleUserInfo;
import com.socialv2.okr.dto.team.TeamDTO;
import com.socialv2.okr.dto.user.UserDTO;
import com.socialv2.okr.entities.roles.Role;
import com.socialv2.okr.entities.roles.RoleEnum;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.entities.workspaces.WorkspaceUser;
import com.socialv2.okr.repository.RoleRepository;
import com.socialv2.okr.repository.user.UserRepository;
import com.socialv2.okr.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDTO getUserById(String userId) {
        var user= userRepository.findUserById(UUID.fromString(userId));
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(team -> mapper.map(team, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUserByIds(List<String> ids) {
        if (Objects.isNull(ids) || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return userRepository.findAll();
    }

    @Override
    public User mergerFromGoogleUserInfo(GoogleUserInfo gUser) {
        User user = userRepository.findUserByEmail(gUser.getEmail())
                .map(existing -> transformFromGUserInfo(existing, gUser))
                .orElseGet(() -> {
                    Role role = roleRepository.findByRoleName(RoleEnum.WORKSPACE_MEMBER.getKey())
                            .orElseThrow(() -> new RuntimeException("Role not found: " + RoleEnum.WORKSPACE_MEMBER));

                    User newUser = new User();
                    newUser = transformFromGUserInfo(newUser, gUser);
                    newUser.setBio(null);
                    newUser.setRole(role);
                    return newUser;
                });

        userRepository.save(user);
        return user;
    }

    private User transformFromGUserInfo(User user, GoogleUserInfo gUser) {
        if (gUser == null) return null;

        user.setEmail(gUser.getEmail());
        user.setFullName(gUser.getName());
        user.setAvatar(gUser.getPicture());
        return user;
    }
}
