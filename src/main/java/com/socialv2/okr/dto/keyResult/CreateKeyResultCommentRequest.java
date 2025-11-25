package com.socialv2.okr.dto.keyResult;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateKeyResultCommentRequest {
    
    @NotBlank(message = "Comment is required")
    private String comment;
    
    @NotNull(message = "Key result ID is required")
    private UUID keyResultId;
    
    @NotNull(message = "User ID is required")
    private UUID userId;
}
