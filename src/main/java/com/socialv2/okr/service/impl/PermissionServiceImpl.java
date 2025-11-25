package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.role.CreatePermissionRequest;
import com.socialv2.okr.dto.role.PermissionDTO;
import com.socialv2.okr.entities.roles.Permission;
import com.socialv2.okr.repository.role.PermissionRepository;
import com.socialv2.okr.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ModelMapper mapper;

    @Override
    public PermissionDTO createPermission(CreatePermissionRequest request) {
        log.info("Creating new permission with name: {}", request.getPermissionName());
        
        permissionRepository.findByPermissionName(request.getPermissionName())
                .ifPresent(permission -> {
                    throw new RuntimeException("Permission already exists with name: " + request.getPermissionName());
                });
        
        Permission permission = Permission.builder()
                .permissionName(request.getPermissionName())
                .description(request.getDescription())
                .build();
        
        Permission savedPermission = permissionRepository.save(permission);
        return mapper.map(savedPermission, PermissionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionDTO getPermissionById(String id) {
        log.info("Fetching permission with id: {}", id);
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        return mapper.map(permission, PermissionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionDTO getPermissionByName(String permissionName) {
        log.info("Fetching permission with name: {}", permissionName);
        Permission permission = permissionRepository.findByPermissionName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission not found with name: " + permissionName));
        return mapper.map(permission, PermissionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionDTO> getAllPermissions() {
        log.info("Fetching all permissions");
        return permissionRepository.findAll().stream()
                .map(permission -> mapper.map(permission, PermissionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePermission(String id) {
        log.info("Deleting permission with id: {}", id);
        
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id: " + id));
        
        permissionRepository.delete(permission);
    }
}
