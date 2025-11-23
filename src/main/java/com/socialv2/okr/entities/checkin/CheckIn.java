package com.socialv2.okr.entities.checkin;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.objectives.Objective;
import com.socialv2.okr.entities.teams.Team;
import com.socialv2.okr.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "CheckIn", schema = "dbo")
public class CheckIn extends BaseEntity {

    @OneToMany(mappedBy = "checkIn", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CheckInDetail> checkInDetails = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "ObjectiveId", referencedColumnName = "Id")
    private Objective objective;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ReporterId", referencedColumnName = "Id")
    private User reporter;
}
