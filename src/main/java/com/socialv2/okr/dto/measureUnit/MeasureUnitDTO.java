package com.socialv2.okr.dto.measureUnit;

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
public class MeasureUnitDTO {
    private UUID id;
    private String name;
    private boolean isDecimal;
    private double startValue;
    private double targetValue;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
