package com.socialv2.okr.controller;

import com.socialv2.okr.dto.role.CreatePermissionRequest;
import com.socialv2.okr.dto.role.CreateRoleRequest;
import com.socialv2.okr.dto.role.PermissionDTO;
import com.socialv2.okr.dto.role.RoleDTO;
import com.socialv2.okr.service.PermissionService;
import com.socialv2.okr.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@Valid @RequestBody CreateRoleRequest request) {
        RoleDTO role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String roleName) {
        RoleDTO role = roleService.getRoleByName(roleName);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/{roleName}/permissions")
    public ResponseEntity<RoleDTO> addPermissionsToRole(
            @PathVariable String roleName,
            @RequestBody List<String> permissionIds) {
        RoleDTO role = roleService.addPermissionsToRole(roleName, permissionIds);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{roleName}/permissions")
    public ResponseEntity<RoleDTO> removePermissionsFromRole(
            @PathVariable String roleName,
            @RequestBody List<String> permissionIds) {
        RoleDTO role = roleService.removePermissionsFromRole(roleName, permissionIds);
        return ResponseEntity.ok(role);
    }

    @DeleteMapping("/{roleName}")
    public ResponseEntity<Void> deleteRole(@PathVariable String roleName) {
        roleService.deleteRole(roleName);
        return ResponseEntity.noContent().build();
    }

    // Permission endpoints
    @PostMapping("/permissions")
    public ResponseEntity<PermissionDTO> createPermission(@Valid @RequestBody CreatePermissionRequest request) {
        PermissionDTO permission = permissionService.createPermission(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(permission);
    }

    @GetMapping("/permissions/{permissionId}")
    public ResponseEntity<PermissionDTO> getPermissionById(@PathVariable UUID permissionId) {
        PermissionDTO permission = permissionService.getPermissionById(permissionId);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/permissions/name/{permissionName}")
    public ResponseEntity<PermissionDTO> getPermissionByName(@PathVariable String permissionName) {
        PermissionDTO permission = permissionService.getPermissionByName(permissionName);
        return ResponseEntity.ok(permission);
    }

    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @DeleteMapping("/permissions/{permissionId}")
    public ResponseEntity<Void> deletePermission(@PathVariable UUID permissionId) {
        permissionService.deletePermission(permissionId);
        return ResponseEntity.noContent().build();
    }
}
