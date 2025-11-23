package com.socialv2.okr.controller;

import com.socialv2.okr.dto.keyResult.CreateKeyResultCommentRequest;
import com.socialv2.okr.dto.keyResult.KeyResultCommentDTO;
import com.socialv2.okr.dto.keyResult.UpdateKeyResultCommentRequest;
import com.socialv2.okr.service.KeyResultCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("key-result-comments")
@RequiredArgsConstructor
public class KeyResultCommentController {

    private final KeyResultCommentService commentService;

    @PostMapping
    public ResponseEntity<KeyResultCommentDTO> createComment(@Valid @RequestBody CreateKeyResultCommentRequest request) {
        KeyResultCommentDTO comment = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<KeyResultCommentDTO> getCommentById(@PathVariable UUID commentId) {
        KeyResultCommentDTO comment = commentService.getCommentById(commentId);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<KeyResultCommentDTO>> getAllComments() {
        List<KeyResultCommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/key-result/{keyResultId}")
    public ResponseEntity<List<KeyResultCommentDTO>> getCommentsByKeyResultId(@PathVariable UUID keyResultId) {
        List<KeyResultCommentDTO> comments = commentService.getCommentsByKeyResultId(keyResultId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<KeyResultCommentDTO>> getCommentsByUserId(@PathVariable UUID userId) {
        List<KeyResultCommentDTO> comments = commentService.getCommentsByUserId(userId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<KeyResultCommentDTO> updateComment(
            @PathVariable UUID commentId,
            @Valid @RequestBody UpdateKeyResultCommentRequest request) {
        KeyResultCommentDTO comment = commentService.updateComment(commentId, request);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
