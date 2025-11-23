package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.workspace.AddWorkspaceUserRequest;
import com.socialv2.okr.dto.workspace.WorkspaceUserDTO;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.entities.workspaces.Workspace;
import com.socialv2.okr.entities.workspaces.WorkspaceUser;
import com.socialv2.okr.repository.user.UserRepository;
import com.socialv2.okr.repository.workspace.WorkspaceRepository;
import com.socialv2.okr.repository.workspace.WorkspaceUserRepository;
import com.socialv2.okr.service.WorkspaceUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WorkspaceUserServiceImpl implements WorkspaceUserService {

    private final WorkspaceUserRepository workspaceUserRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public WorkspaceUserDTO addUserToWorkspace(AddWorkspaceUserRequest request) {
        log.info("Adding user {} to workspace {}", request.getUserId(), request.getWorkspaceId());
        
        // Check if already exists
        workspaceUserRepository.findByUserIdAndWorkspaceId(request.getUserId(), request.getWorkspaceId())
                .ifPresent(wu -> {
                    throw new RuntimeException("User already belongs to this workspace");
                });
        
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        
        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                .orElseThrow(() -> new RuntimeException("Workspace not found with id: " + request.getWorkspaceId()));
        
        WorkspaceUser workspaceUser = WorkspaceUser.builder()
                .user(user)
                .workspace(workspace)
                .build();
        
        WorkspaceUser savedWorkspaceUser = workspaceUserRepository.save(workspaceUser);
        WorkspaceUserDTO dto = mapper.map(savedWorkspaceUser, WorkspaceUserDTO.class);
        dto.setWorkspaceId(workspace.getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public WorkspaceUserDTO getWorkspaceUserById(UUID id) {
        log.info("Fetching workspace user with id: {}", id);
        WorkspaceUser workspaceUser = workspaceUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace user not found with id: " + id));
        
        WorkspaceUserDTO dto = mapper.map(workspaceUser, WorkspaceUserDTO.class);
        dto.setWorkspaceId(workspaceUser.getWorkspace().getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceUserDTO> getAllWorkspaceUsers() {
        log.info("Fetching all workspace users");
        return workspaceUserRepository.findAll().stream()
                .map(workspaceUser -> {
                    WorkspaceUserDTO dto = mapper.map(workspaceUser, WorkspaceUserDTO.class);
                    dto.setWorkspaceId(workspaceUser.getWorkspace().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceUserDTO> getWorkspaceUsersByWorkspaceId(UUID workspaceId) {
        log.info("Fetching workspace users for workspace: {}", workspaceId);
        return workspaceUserRepository.findByWorkspaceId(workspaceId).stream()
                .map(workspaceUser -> {
                    WorkspaceUserDTO dto = mapper.map(workspaceUser, WorkspaceUserDTO.class);
                    dto.setWorkspaceId(workspaceUser.getWorkspace().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkspaceUserDTO> getWorkspaceUsersByUserId(UUID userId) {
        log.info("Fetching workspace users for user: {}", userId);
        return workspaceUserRepository.findByUserId(userId).stream()
                .map(workspaceUser -> {
                    WorkspaceUserDTO dto = mapper.map(workspaceUser, WorkspaceUserDTO.class);
                    dto.setWorkspaceId(workspaceUser.getWorkspace().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserFromWorkspace(UUID id) {
        log.info("Removing workspace user with id: {}", id);
        
        WorkspaceUser workspaceUser = workspaceUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace user not found with id: " + id));
        
        workspaceUser.setDeleted(true);
        workspaceUserRepository.save(workspaceUser);
    }

    @Override
    public void removeUserFromWorkspaceByUserAndWorkspace(UUID userId, UUID workspaceId) {
        log.info("Removing user {} from workspace {}", userId, workspaceId);
        
        WorkspaceUser workspaceUser = workspaceUserRepository.findByUserIdAndWorkspaceId(userId, workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace user relationship not found"));
        
        workspaceUser.setDeleted(true);
        workspaceUserRepository.save(workspaceUser);
    }
}
