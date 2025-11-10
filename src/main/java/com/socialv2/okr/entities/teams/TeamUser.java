package com.socialv2.okr.entities.teams;

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
@Table(name = "TeamUser", schema = "dbo")
public class TeamUser extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TeamId", referencedColumnName = "Id", nullable = false)
    private Team team;
}
