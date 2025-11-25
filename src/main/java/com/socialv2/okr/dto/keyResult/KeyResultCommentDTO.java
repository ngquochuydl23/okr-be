package com.socialv2.okr.dto.keyResult;

import com.socialv2.okr.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyResultCommentDTO {
    private UUID id;
    private String comment;
    private UUID keyResultId;
    private UserDTO user;
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
