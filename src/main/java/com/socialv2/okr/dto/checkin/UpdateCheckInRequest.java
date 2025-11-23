package com.socialv2.okr.dto.checkin;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCheckInRequest {
    
    private UUID objectiveId;
    
    private UUID reporterId;
    
    @Valid
    private Set<CreateCheckInDetailRequest> checkInDetails;
}
