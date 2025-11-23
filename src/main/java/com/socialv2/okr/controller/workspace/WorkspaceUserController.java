package com.socialv2.okr.controller.workspace;

import com.socialv2.okr.dto.workspace.AddWorkspaceUserRequest;
import com.socialv2.okr.dto.workspace.WorkspaceUserDTO;
import com.socialv2.okr.service.WorkspaceUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("workspace-users")
@RequiredArgsConstructor
public class WorkspaceUserController {

    private final WorkspaceUserService workspaceUserService;

    @PostMapping
    public ResponseEntity<WorkspaceUserDTO> addUserToWorkspace(@Valid @RequestBody AddWorkspaceUserRequest request) {
        WorkspaceUserDTO workspaceUser = workspaceUserService.addUserToWorkspace(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(workspaceUser);
    }

    @GetMapping("/{workspaceUserId}")
    public ResponseEntity<WorkspaceUserDTO> getWorkspaceUserById(@PathVariable UUID workspaceUserId) {
        WorkspaceUserDTO workspaceUser = workspaceUserService.getWorkspaceUserById(workspaceUserId);
        return ResponseEntity.ok(workspaceUser);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceUserDTO>> getAllWorkspaceUsers() {
        List<WorkspaceUserDTO> workspaceUsers = workspaceUserService.getAllWorkspaceUsers();
        return ResponseEntity.ok(workspaceUsers);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<WorkspaceUserDTO>> getWorkspaceUsersByWorkspaceId(@PathVariable UUID workspaceId) {
        List<WorkspaceUserDTO> workspaceUsers = workspaceUserService.getWorkspaceUsersByWorkspaceId(workspaceId);
        return ResponseEntity.ok(workspaceUsers);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkspaceUserDTO>> getWorkspaceUsersByUserId(@PathVariable UUID userId) {
        List<WorkspaceUserDTO> workspaceUsers = workspaceUserService.getWorkspaceUsersByUserId(userId);
        return ResponseEntity.ok(workspaceUsers);
    }

    @DeleteMapping("/{workspaceUserId}")
    public ResponseEntity<Void> removeUserFromWorkspace(@PathVariable UUID workspaceUserId) {
        workspaceUserService.removeUserFromWorkspace(workspaceUserId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workspace/{workspaceId}/user/{userId}")
    public ResponseEntity<Void> removeUserFromWorkspaceByIds(
            @PathVariable UUID workspaceId,
            @PathVariable UUID userId) {
        workspaceUserService.removeUserFromWorkspaceByUserAndWorkspace(userId, workspaceId);
        return ResponseEntity.noContent().build();
    }
}
