package com.socialv2.okr.repository;

import com.socialv2.okr.entities.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    @Query("SELECT r FROM Role r WHERE r.roleName = :name")
    Optional<Role> findByRoleName(String name);
}
