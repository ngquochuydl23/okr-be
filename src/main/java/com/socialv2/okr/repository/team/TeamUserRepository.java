package com.socialv2.okr.repository.team;

import com.socialv2.okr.entities.teams.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamUserRepository extends JpaRepository<TeamUser, UUID>, JpaSpecificationExecutor<TeamUser> {
    Optional<TeamUser> findById(UUID id);
    
    List<TeamUser> findByTeamId(UUID teamId);
    
    List<TeamUser> findByUserId(UUID userId);
    
    Optional<TeamUser> findByUserIdAndTeamId(UUID userId, UUID teamId);
    
    List<TeamUser> findAll();
}
