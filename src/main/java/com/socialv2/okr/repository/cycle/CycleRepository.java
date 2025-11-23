package com.socialv2.okr.repository.cycle;

import com.socialv2.okr.entities.cycles.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, UUID>, JpaSpecificationExecutor<Cycle> {
    Optional<Cycle> findById(UUID id);
    
    List<Cycle> findByWorkspaceId(UUID workspaceId);
    
    List<Cycle> findAll();
}
