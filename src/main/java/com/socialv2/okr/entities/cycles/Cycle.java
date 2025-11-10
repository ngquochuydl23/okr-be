package com.socialv2.okr.entities.cycles;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.workspaces.Workspace;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "Cycle", schema = "dbo")
public class Cycle extends BaseEntity {

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "WorkspaceId", referencedColumnName = "Id")
    private Workspace workspace;
}
