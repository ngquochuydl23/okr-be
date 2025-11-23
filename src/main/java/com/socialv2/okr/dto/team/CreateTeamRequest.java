package com.socialv2.okr.dto.team;

import jakarta.validation.constraints.NotBlank;
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
public class CreateTeamRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    private UUID parentTeamId;
    
    @NotNull(message = "Workspace ID is required")
    private UUID workspaceId;
}
