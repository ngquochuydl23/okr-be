package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.keyResult.CreateKeyResultCommentRequest;
import com.socialv2.okr.dto.keyResult.KeyResultCommentDTO;
import com.socialv2.okr.dto.keyResult.UpdateKeyResultCommentRequest;
import com.socialv2.okr.entities.keyResults.KeyResult;
import com.socialv2.okr.entities.keyResults.KeyResultComment;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.repository.keyResult.KeyResultCommentRepository;
import com.socialv2.okr.repository.objective.KeyResultRepository;
import com.socialv2.okr.repository.user.UserRepository;
import com.socialv2.okr.service.KeyResultCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class KeyResultCommentServiceImpl implements KeyResultCommentService {

    private final KeyResultCommentRepository commentRepository;
    private final KeyResultRepository keyResultRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public KeyResultCommentDTO createComment(CreateKeyResultCommentRequest request) {
        log.info("Creating new comment for key result: {}", request.getKeyResultId());
        
        KeyResult keyResult = keyResultRepository.findById(request.getKeyResultId())
                .orElseThrow(() -> new RuntimeException("Key result not found with id: " + request.getKeyResultId()));
        
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        
        KeyResultComment comment = KeyResultComment.builder()
                .comment(request.getComment())
                .keyResult(keyResult)
                .user(user)
                .build();
        
        KeyResultComment savedComment = commentRepository.save(comment);
        return mapper.map(savedComment, KeyResultCommentDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public KeyResultCommentDTO getCommentById(UUID id) {
        log.info("Fetching comment with id: {}", id);
        KeyResultComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        return mapper.map(comment, KeyResultCommentDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeyResultCommentDTO> getAllComments() {
        log.info("Fetching all comments");
        return commentRepository.findAll().stream()
                .map(comment -> mapper.map(comment, KeyResultCommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeyResultCommentDTO> getCommentsByKeyResultId(UUID keyResultId) {
        log.info("Fetching comments for key result: {}", keyResultId);
        return commentRepository.findByKeyResultId(keyResultId).stream()
                .map(comment -> mapper.map(comment, KeyResultCommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeyResultCommentDTO> getCommentsByUserId(UUID userId) {
        log.info("Fetching comments for user: {}", userId);
        return commentRepository.findByUserId(userId).stream()
                .map(comment -> mapper.map(comment, KeyResultCommentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public KeyResultCommentDTO updateComment(UUID id, UpdateKeyResultCommentRequest request) {
        log.info("Updating comment with id: {}", id);
        
        KeyResultComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        comment.setComment(request.getComment());
        
        KeyResultComment updatedComment = commentRepository.save(comment);
        return mapper.map(updatedComment, KeyResultCommentDTO.class);
    }

    @Override
    public void deleteComment(UUID id) {
        log.info("Deleting comment with id: {}", id);
        
        KeyResultComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        comment.setDeleted(true);
        commentRepository.save(comment);
    }
}
