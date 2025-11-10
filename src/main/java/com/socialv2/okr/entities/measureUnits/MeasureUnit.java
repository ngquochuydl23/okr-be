package com.socialv2.okr.entities.measureUnits;

import com.socialv2.okr.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "KeyResult", schema = "dbo")
public class MeasureUnit extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "IsDecimal")
    private boolean isDecimal;

    @Column(name = "StartValue")
    private double startValue;

    @Column(name = "TargetValue")
    private double targetValue;
}
