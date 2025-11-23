package com.socialv2.okr.repository.checkin;

import com.socialv2.okr.entities.checkin.CheckInDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CheckInDetailRepository extends JpaRepository<CheckInDetail, UUID>, JpaSpecificationExecutor<CheckInDetail> {
    List<CheckInDetail> findByCheckInId(UUID checkInId);
    List<CheckInDetail> findByKeyResultId(UUID keyResultId);
}
