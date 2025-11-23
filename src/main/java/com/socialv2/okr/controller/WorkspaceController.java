package com.socialv2.okr.controller;

import com.socialv2.okr.dto.workspace.WorkspaceDTO;
import com.socialv2.okr.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @GetMapping("/{workspaceId}")
    public ResponseEntity<WorkspaceDTO> getWorkspaceById(@PathVariable String workspaceId) {
        return ResponseEntity.ok(workspaceService.getWorkspaceById(workspaceId));
    }
}
