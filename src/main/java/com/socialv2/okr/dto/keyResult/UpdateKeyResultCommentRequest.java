package com.socialv2.okr.dto.keyResult;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateKeyResultCommentRequest {
    
    @NotBlank(message = "Comment is required")
    private String comment;
}
