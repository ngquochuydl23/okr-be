package com.socialv2.okr.entities.objectives;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.cycles.Cycle;
import com.socialv2.okr.entities.keyResults.KeyResult;
import com.socialv2.okr.entities.teams.Team;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.entities.workspaces.Workspace;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "Objective", schema = "dbo")
public class Objective extends BaseEntity {

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Progress", nullable = false)
    private double progress = 0.0;

    @OneToMany(mappedBy = "objective")
    private Set<KeyResult> keyResults = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "ParentObjectiveId")
    private Objective parentObjective;

    @OneToMany(mappedBy = "parentObjective")
    private Set<Objective> childObjectives = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "TeamId", referencedColumnName = "Id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "WorkspaceId", referencedColumnName = "Id")
    private Workspace workspace;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CycleId", referencedColumnName = "Id")
    private Cycle cycle;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatorId", referencedColumnName = "Id")
    private User creator;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ResponsibleUserId", referencedColumnName = "Id")
    private User responsibleUser;

    @Transient
    public Set<User> getSupporters() {
        return keyResults.stream()
                .flatMap(keyResult -> keyResult.getSupporters().stream())
                .collect(Collectors.toSet());
    }

}
