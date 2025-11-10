package com.socialv2.okr.entities.users;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.keyResults.KeyResult;
import com.socialv2.okr.entities.roles.Role;
import com.socialv2.okr.entities.teams.TeamUser;
import com.socialv2.okr.entities.workspaces.WorkspaceUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MUser", schema = "dbo")
public class User extends BaseEntity {

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Avatar")
    private String avatar;

    @Column(name = "Bio")
    private String bio;

    @OneToOne
    @JoinColumn(name = "RoleName", referencedColumnName = "RoleName")
    private Role role;

    @OneToMany(mappedBy = "user" , orphanRemoval = true)
    private Set<WorkspaceUser> workspaceUsers = new HashSet<>();

    @OneToMany(mappedBy = "user",  orphanRemoval = true)
    private Set<TeamUser> teamUsers = new HashSet<>();

    @ManyToMany(mappedBy = "supporters")
    private Set<KeyResult> supportingKeyResults = new HashSet<>();
}
