package com.socialv2.okr.entities.workspaces;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.teams.Team;
import com.socialv2.okr.entities.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Workspace", schema = "dbo")
public class Workspace extends BaseEntity {

    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Type", nullable = false)
    private WorkspaceTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "OwnerId", referencedColumnName = "Id")
    private User owner;

    @OneToMany(mappedBy = "workspace", orphanRemoval = true)
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "workspace")
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();
}
