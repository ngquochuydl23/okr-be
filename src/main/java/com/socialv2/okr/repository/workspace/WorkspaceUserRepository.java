package com.socialv2.okr.repository.workspace;

import com.socialv2.okr.entities.workspaces.WorkspaceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID>, JpaSpecificationExecutor<WorkspaceUser> {
    Optional<WorkspaceUser> findById(UUID id);
    
    List<WorkspaceUser> findByWorkspaceId(UUID workspaceId);
    
    List<WorkspaceUser> findByUserId(UUID userId);
    
    Optional<WorkspaceUser> findByUserIdAndWorkspaceId(UUID userId, UUID workspaceId);
    
    List<WorkspaceUser> findAll();
}
