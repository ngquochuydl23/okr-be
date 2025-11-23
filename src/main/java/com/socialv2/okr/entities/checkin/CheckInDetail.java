package com.socialv2.okr.entities.checkin;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.keyResults.KeyResult;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "CheckInDetail", schema = "dbo")
public class CheckInDetail extends BaseEntity {

    @Column(name = "Change")
    private double change;

    @Column(name = "Progress")
    private double progress;

    @Column(name = "ObtainedValue")
    private double obtainedValue;

    @Column(name = "ConfidenceLevelEnum")
    private ConfidenceLevelEnum confidenceLevel;

    @ManyToOne
    @JoinColumn(name = "CheckInId", referencedColumnName = "Id")
    private CheckIn checkIn;

    @ManyToOne
    @JoinColumn(name = "KeyResultId", referencedColumnName = "Id")
    private KeyResult keyResult;
}
