package com.socialv2.okr.service;

import com.socialv2.okr.dto.keyResult.CreateKeyResultCommentRequest;
import com.socialv2.okr.dto.keyResult.KeyResultCommentDTO;
import com.socialv2.okr.dto.keyResult.UpdateKeyResultCommentRequest;

import java.util.List;
import java.util.UUID;

public interface KeyResultCommentService {
    KeyResultCommentDTO createComment(CreateKeyResultCommentRequest request);
    
    KeyResultCommentDTO getCommentById(UUID id);
    
    List<KeyResultCommentDTO> getAllComments();
    
    List<KeyResultCommentDTO> getCommentsByKeyResultId(UUID keyResultId);
    
    List<KeyResultCommentDTO> getCommentsByUserId(UUID userId);
    
    KeyResultCommentDTO updateComment(UUID id, UpdateKeyResultCommentRequest request);
    
    void deleteComment(UUID id);
}
