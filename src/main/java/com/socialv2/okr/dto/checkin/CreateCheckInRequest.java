package com.socialv2.okr.dto.checkin;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
public class CreateCheckInRequest {
    
    @NotNull(message = "Objective ID is required")
    private UUID objectiveId;
    
    @NotNull(message = "Reporter ID is required")
    private UUID reporterId;
    
    @Valid
    private Set<CreateCheckInDetailRequest> checkInDetails;
}
