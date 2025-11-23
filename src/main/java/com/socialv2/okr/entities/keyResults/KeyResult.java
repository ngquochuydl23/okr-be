package com.socialv2.okr.entities.keyResults;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.checkin.CheckIn;
import com.socialv2.okr.entities.measureUnits.MeasureUnit;
import com.socialv2.okr.entities.objectives.Objective;
import com.socialv2.okr.entities.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "KeyResult", schema = "dbo")
public class KeyResult extends BaseEntity {

    @Nationalized
    @Column(name = "Name")
    private String name;

    @Column(name = "Progress")
    private double progress = 0.0;

    @Column(name = "Target", nullable = false)
    private double target = 0.0;

    @Column(name = "ObtainedValue", nullable = false)
    private double obtainedValue = 0.0;

    @Column(name = "KeyResultOrder")
    private int keyResultOrder;

    @Column(name = "Change")
    private double change;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MeasureUnitId", referencedColumnName = "Id")
    private MeasureUnit measureUnit;

    @ManyToOne
    @JoinColumn(name = "ObjectiveId", referencedColumnName = "Id")
    private Objective objective;

    @ManyToMany
    @JoinTable(
            name = "KeyResultSupporter",
            joinColumns = @JoinColumn(name = "KeyResultId", referencedColumnName = "Id"),
            inverseJoinColumns = @JoinColumn(name = "UserId", referencedColumnName = "Id")
    )
    private Set<User> supporters = new HashSet<>();

    @OneToMany(mappedBy = "keyResult")
    private Set<KeyResultComment> comments = new HashSet<>();
}
