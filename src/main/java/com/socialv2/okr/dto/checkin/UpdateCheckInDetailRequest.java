package com.socialv2.okr.dto.checkin;

import com.socialv2.okr.entities.checkin.ConfidenceLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCheckInDetailRequest {
    
    private Double change;
    
    private Double progress;
    
    private Double obtainedValue;
    
    private ConfidenceLevelEnum confidenceLevel;
}
