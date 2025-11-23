package com.socialv2.okr.service;

import com.socialv2.okr.dto.workspace.AddWorkspaceUserRequest;
import com.socialv2.okr.dto.workspace.WorkspaceUserDTO;

import java.util.List;
import java.util.UUID;

public interface WorkspaceUserService {
    WorkspaceUserDTO addUserToWorkspace(AddWorkspaceUserRequest request);
    
    WorkspaceUserDTO getWorkspaceUserById(UUID id);
    
    List<WorkspaceUserDTO> getAllWorkspaceUsers();
    
    List<WorkspaceUserDTO> getWorkspaceUsersByWorkspaceId(UUID workspaceId);
    
    List<WorkspaceUserDTO> getWorkspaceUsersByUserId(UUID userId);
    
    void removeUserFromWorkspace(UUID id);
    
    void removeUserFromWorkspaceByUserAndWorkspace(UUID userId, UUID workspaceId);
}
