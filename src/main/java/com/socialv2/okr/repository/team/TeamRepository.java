package com.socialv2.okr.repository.team;

import com.socialv2.okr.entities.teams.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID>, JpaSpecificationExecutor<Team> {
}
