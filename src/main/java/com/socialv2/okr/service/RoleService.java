package com.socialv2.okr.service;

import com.socialv2.okr.dto.role.CreateRoleRequest;
import com.socialv2.okr.dto.role.RoleDTO;

import java.util.List;

public interface RoleService {
    RoleDTO createRole(CreateRoleRequest request);
    
    RoleDTO getRoleByName(String roleName);
    
    List<RoleDTO> getAllRoles();
    
    RoleDTO addPermissionsToRole(String roleName, List<String> permissionIds);
    
    RoleDTO removePermissionsFromRole(String roleName, List<String> permissionIds);
    
    void deleteRole(String roleName);
}
