package com.socialv2.okr.dto.team;

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
public class AddTeamUserRequest {
    @NotNull(message = "User ID is required")
    private UUID userId;
    
    @NotNull(message = "Team ID is required")
    private UUID teamId;
}
