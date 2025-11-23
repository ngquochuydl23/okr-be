package com.socialv2.okr.entities.roles;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "Role", schema = "dbo")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "RoleName", nullable = false, unique = true)
    private String roleName;

    @Column(name = "Description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "RolePermission",
            schema = "dbo",
            joinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "RoleName"),
            inverseJoinColumns = @JoinColumn(name = "PermissionId", referencedColumnName = "Id")
    )
    private Set<Permission> permissions = new HashSet<>();
}
