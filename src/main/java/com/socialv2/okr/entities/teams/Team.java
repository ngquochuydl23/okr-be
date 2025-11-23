package com.socialv2.okr.entities.teams;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.workspaces.Workspace;
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
@Table(name = "Team", schema = "dbo")
public class Team extends BaseEntity {

    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamUser> teamUsers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ParentTeamId", referencedColumnName = "Id")
    private Team parentTeam;

    @OneToMany(mappedBy = "parentTeam", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> subTeams = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "WorkspaceId", referencedColumnName = "Id")
    private Workspace workspace;
}
