package com.socialv2.okr.entities.workspaces;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "WorkspaceUser", schema = "dbo")
public class WorkspaceUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "WorkspaceId", referencedColumnName = "Id")
    private Workspace workspace;
}
