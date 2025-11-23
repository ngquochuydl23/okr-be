package com.socialv2.okr.dto.checkin;

import com.socialv2.okr.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckInDTO {
    private UUID id;
    private UUID objectiveId;
    private UserDTO reporter;
    private Set<CheckInDetailDTO> checkInDetails;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
