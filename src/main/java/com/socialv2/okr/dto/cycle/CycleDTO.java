package com.socialv2.okr.dto.cycle;

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
public class CycleDTO {
    private UUID id;
    private String name;
    private String description;
    private WorkspaceDTO workspace;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
