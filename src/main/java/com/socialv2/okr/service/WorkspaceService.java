package com.socialv2.okr.service;

import com.socialv2.okr.dto.workspace.WorkspaceDTO;
import com.socialv2.okr.entities.workspaces.Workspace;

public interface WorkspaceService {
    WorkspaceDTO createOrUpdateWorkspace(Workspace workspace);

    WorkspaceDTO getWorkspaceById(String id);
}
