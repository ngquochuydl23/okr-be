package com.socialv2.okr.entities.keyResults;

import com.socialv2.okr.entities.BaseEntity;
import com.socialv2.okr.entities.users.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "KeyResultComment", schema = "dbo")
public class KeyResultComment extends BaseEntity {

    @Nationalized
    @Column(name = "Comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "KeyResultId", referencedColumnName = "Id")
    private KeyResult keyResult;

    @ManyToOne
    @JoinColumn(name = "UserId", referencedColumnName = "Id")
    private User user;
}
