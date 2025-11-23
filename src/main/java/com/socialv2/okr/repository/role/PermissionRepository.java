package com.socialv2.okr.repository.role;

import com.socialv2.okr.entities.roles.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID>, JpaSpecificationExecutor<Permission> {
    Optional<Permission> findByPermissionName(String permissionName);
    
    List<Permission> findByIdIn(List<UUID> ids);
}
