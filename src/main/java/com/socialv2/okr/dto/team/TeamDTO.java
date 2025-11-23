package com.socialv2.okr.dto.team;

import com.socialv2.okr.dto.workspace.WorkspaceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private UUID id;
    private String name;
    private UUID parentTeamId;
    private WorkspaceDTO workspace;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
