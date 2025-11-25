package com.socialv2.okr.service;

import com.socialv2.okr.dto.checkin.CheckInDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInRequest;

import java.util.List;
import java.util.UUID;

public interface CheckInService {
    CheckInDTO createCheckIn(CreateCheckInRequest request);
    
    CheckInDTO getCheckInById(UUID id);
    
    List<CheckInDTO> getAllCheckIns();
    
    List<CheckInDTO> getCheckInsByObjectiveId(UUID objectiveId);
    
    List<CheckInDTO> getCheckInsByReporterId(UUID reporterId);
    
    CheckInDTO updateCheckIn(UUID id, UpdateCheckInRequest request);
    
    void deleteCheckIn(UUID id);
}
