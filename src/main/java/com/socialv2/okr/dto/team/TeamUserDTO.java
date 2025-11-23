package com.socialv2.okr.dto.team;

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
public class TeamUserDTO {
    private UUID id;
    private UserDTO user;
    private UUID teamId;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
