package com.socialv2.okr.dto.measureUnit;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeasureUnitRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    private boolean isDecimal;
    
    private double startValue;
    
    private double targetValue;
}
