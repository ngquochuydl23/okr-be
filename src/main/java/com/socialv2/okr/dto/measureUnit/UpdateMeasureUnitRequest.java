package com.socialv2.okr.dto.measureUnit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMeasureUnitRequest {
    private String name;
    private Boolean isDecimal;
    private Double startValue;
    private Double targetValue;
}
