package com.socialv2.okr.repository.role;

import com.socialv2.okr.entities.roles.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
    Optional<Permission> findByPermissionName(String permissionName);
}
