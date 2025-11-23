package com.socialv2.okr.dto.workspace;

import com.socialv2.okr.dto.user.UserDTO;
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
public class WorkspaceUserDTO {
    private UUID id;
    private UserDTO user;
    private UUID workspaceId;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
