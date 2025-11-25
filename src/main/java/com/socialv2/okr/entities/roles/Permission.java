package com.socialv2.okr.entities.roles;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "Permission", schema = "dbo")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Name", nullable = false, unique = true)
    private String permissionName;

    @Column(name = "Description")
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();
}
