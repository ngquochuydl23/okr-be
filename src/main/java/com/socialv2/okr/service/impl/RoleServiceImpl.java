package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.role.CreateRoleRequest;
import com.socialv2.okr.dto.role.RoleDTO;
import com.socialv2.okr.entities.roles.Permission;
import com.socialv2.okr.entities.roles.Role;
import com.socialv2.okr.repository.RoleRepository;
import com.socialv2.okr.repository.role.PermissionRepository;
import com.socialv2.okr.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ModelMapper mapper;

    @Override
    public RoleDTO createRole(CreateRoleRequest request) {
        log.info("Creating new role with name: {}", request.getRoleName());
        
        roleRepository.findById(request.getRoleName())
                .ifPresent(role -> {
                    throw new RuntimeException("Role already exists with name: " + request.getRoleName());
                });
        
        Set<Permission> permissions = new HashSet<>();
        if (request.getPermissionIds() != null && !request.getPermissionIds().isEmpty()) {
            permissions = new HashSet<>(permissionRepository.findByIdIn(
                    request.getPermissionIds().stream().collect(Collectors.toList())
            ));
        }
        
        Role role = Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .permissions(permissions)
                .build();
        
        Role savedRole = roleRepository.save(role);
        return mapper.map(savedRole, RoleDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDTO getRoleByName(String roleName) {
        log.info("Fetching role with name: {}", roleName);
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
        return mapper.map(role, RoleDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDTO> getAllRoles() {
        log.info("Fetching all roles");
        return roleRepository.findAll().stream()
                .map(role -> mapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleDTO addPermissionsToRole(String roleName, List<String> permissionIds) {
        log.info("Adding permissions to role: {}", roleName);
        
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
        
        List<UUID> uuidPermissionIds = permissionIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
        
        List<Permission> permissions = permissionRepository.findByIdIn(uuidPermissionIds);
        role.getPermissions().addAll(permissions);
        
        Role updatedRole = roleRepository.save(role);
        return mapper.map(updatedRole, RoleDTO.class);
    }

    @Override
    public RoleDTO removePermissionsFromRole(String roleName, List<String> permissionIds) {
        log.info("Removing permissions from role: {}", roleName);
        
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
        
        Set<UUID> uuidPermissionIds = permissionIds.stream()
                .map(UUID::fromString)
                .collect(Collectors.toSet());
        
        role.getPermissions().removeIf(permission -> uuidPermissionIds.contains(permission.getId()));
        
        Role updatedRole = roleRepository.save(role);
        return mapper.map(updatedRole, RoleDTO.class);
    }

    @Override
    public void deleteRole(String roleName) {
        log.info("Deleting role with name: {}", roleName);
        
        Role role = roleRepository.findById(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
        
        roleRepository.delete(role);
    }
}
