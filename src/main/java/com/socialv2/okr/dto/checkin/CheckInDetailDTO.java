package com.socialv2.okr.dto.checkin;

import com.socialv2.okr.entities.checkin.ConfidenceLevelEnum;
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
public class CheckInDetailDTO {
    private UUID id;
    private double change;
    private double progress;
    private double obtainedValue;
    private ConfidenceLevelEnum confidenceLevel;
    private UUID checkInId;
    private UUID keyResultId;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
