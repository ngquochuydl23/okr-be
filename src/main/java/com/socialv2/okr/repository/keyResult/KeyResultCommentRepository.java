package com.socialv2.okr.repository.keyResult;

import com.socialv2.okr.entities.keyResults.KeyResultComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KeyResultCommentRepository extends JpaRepository<KeyResultComment, UUID>, JpaSpecificationExecutor<KeyResultComment> {
    List<KeyResultComment> findByKeyResultId(UUID keyResultId);
    List<KeyResultComment> findByUserId(UUID userId);
}
