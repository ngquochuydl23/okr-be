package com.socialv2.okr.dto.workspace;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddWorkspaceUserRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;
    
    @NotNull(message = "Workspace ID is required")
    private UUID workspaceId;
}
