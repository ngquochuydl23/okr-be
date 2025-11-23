package com.socialv2.okr.dto.checkin;

import com.socialv2.okr.entities.checkin.ConfidenceLevelEnum;
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
public class CreateCheckInDetailRequest {
    
    @NotNull(message = "Change value is required")
    private Double change;
    
    @NotNull(message = "Progress value is required")
    private Double progress;
    
    @NotNull(message = "Obtained value is required")
    private Double obtainedValue;
    
    @NotNull(message = "Confidence level is required")
    private ConfidenceLevelEnum confidenceLevel;
    
    @NotNull(message = "Key result ID is required")
    private UUID keyResultId;
}
