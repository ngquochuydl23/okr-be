package com.socialv2.okr.service;

import com.socialv2.okr.dto.checkin.CheckInDetailDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInDetailRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInDetailRequest;

import java.util.List;
import java.util.UUID;

public interface CheckInDetailService {
    CheckInDetailDTO createCheckInDetail(UUID checkInId, CreateCheckInDetailRequest request);
    
    CheckInDetailDTO getCheckInDetailById(UUID id);
    
    List<CheckInDetailDTO> getAllCheckInDetails();
    
    List<CheckInDetailDTO> getCheckInDetailsByCheckInId(UUID checkInId);
    
    List<CheckInDetailDTO> getCheckInDetailsByKeyResultId(UUID keyResultId);
    
    CheckInDetailDTO updateCheckInDetail(UUID id, UpdateCheckInDetailRequest request);
    
    void deleteCheckInDetail(UUID id);
}
