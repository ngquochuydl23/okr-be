package com.socialv2.okr.service;

import com.socialv2.okr.dto.role.CreatePermissionRequest;
import com.socialv2.okr.dto.role.PermissionDTO;

import java.util.List;
import java.util.UUID;

public interface PermissionService {
    PermissionDTO createPermission(CreatePermissionRequest request);
    
    PermissionDTO getPermissionById(String id);
    
    PermissionDTO getPermissionByName(String permissionName);
    
    List<PermissionDTO> getAllPermissions();
    
    void deletePermission(String id);
}
