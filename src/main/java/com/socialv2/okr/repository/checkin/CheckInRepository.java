package com.socialv2.okr.repository.checkin;

import com.socialv2.okr.entities.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, UUID>, JpaSpecificationExecutor<CheckIn> {
    List<CheckIn> findByObjectiveId(UUID objectiveId);
    List<CheckIn> findByReporterId(UUID reporterId);
}
