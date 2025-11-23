package com.socialv2.okr.repository.workspace;

import com.socialv2.okr.entities.workspaces.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, UUID>, JpaSpecificationExecutor<Workspace> {
    @Query("SELECT wp FROM Workspace wp WHERE wp.id = :id")
    Workspace findWorkspaceById(UUID id);
}
