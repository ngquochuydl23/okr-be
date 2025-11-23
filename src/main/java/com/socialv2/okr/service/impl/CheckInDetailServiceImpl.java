package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.checkin.CheckInDetailDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInDetailRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInDetailRequest;
import com.socialv2.okr.entities.checkin.CheckIn;
import com.socialv2.okr.entities.checkin.CheckInDetail;
import com.socialv2.okr.entities.keyResults.KeyResult;
import com.socialv2.okr.repository.checkin.CheckInDetailRepository;
import com.socialv2.okr.repository.checkin.CheckInRepository;
import com.socialv2.okr.repository.objective.KeyResultRepository;
import com.socialv2.okr.service.CheckInDetailService;
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
public class CheckInDetailServiceImpl implements CheckInDetailService {

    private final CheckInDetailRepository checkInDetailRepository;
    private final CheckInRepository checkInRepository;
    private final KeyResultRepository keyResultRepository;
    private final ModelMapper mapper;

    @Override
    public CheckInDetailDTO createCheckInDetail(UUID checkInId, CreateCheckInDetailRequest request) {
        log.info("Creating new check-in detail for check-in: {}", checkInId);
        
        CheckIn checkIn = checkInRepository.findById(checkInId)
                .orElseThrow(() -> new RuntimeException("Check-in not found with id: " + checkInId));
        
        KeyResult keyResult = keyResultRepository.findById(request.getKeyResultId())
                .orElseThrow(() -> new RuntimeException("Key result not found with id: " + request.getKeyResultId()));
        
        CheckInDetail detail = CheckInDetail.builder()
                .change(request.getChange())
                .progress(request.getProgress())
                .obtainedValue(request.getObtainedValue())
                .confidenceLevel(request.getConfidenceLevel())
                .keyResult(keyResult)
                .checkIn(checkIn)
                .build();
        
        CheckInDetail savedDetail = checkInDetailRepository.save(detail);
        return mapper.map(savedDetail, CheckInDetailDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CheckInDetailDTO getCheckInDetailById(UUID id) {
        log.info("Fetching check-in detail with id: {}", id);
        CheckInDetail detail = checkInDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in detail not found with id: " + id));
        
        return mapper.map(detail, CheckInDetailDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDetailDTO> getAllCheckInDetails() {
        log.info("Fetching all check-in details");
        return checkInDetailRepository.findAll().stream()
                .map(detail -> mapper.map(detail, CheckInDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDetailDTO> getCheckInDetailsByCheckInId(UUID checkInId) {
        log.info("Fetching check-in details for check-in: {}", checkInId);
        return checkInDetailRepository.findByCheckInId(checkInId).stream()
                .map(detail -> mapper.map(detail, CheckInDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDetailDTO> getCheckInDetailsByKeyResultId(UUID keyResultId) {
        log.info("Fetching check-in details for key result: {}", keyResultId);
        return checkInDetailRepository.findByKeyResultId(keyResultId).stream()
                .map(detail -> mapper.map(detail, CheckInDetailDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CheckInDetailDTO updateCheckInDetail(UUID id, UpdateCheckInDetailRequest request) {
        log.info("Updating check-in detail with id: {}", id);
        
        CheckInDetail detail = checkInDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in detail not found with id: " + id));
        
        if (request.getChange() != null) {
            detail.setChange(request.getChange());
        }
        
        if (request.getProgress() != null) {
            detail.setProgress(request.getProgress());
        }
        
        if (request.getObtainedValue() != null) {
            detail.setObtainedValue(request.getObtainedValue());
        }
        
        if (request.getConfidenceLevel() != null) {
            detail.setConfidenceLevel(request.getConfidenceLevel());
        }
        
        CheckInDetail updatedDetail = checkInDetailRepository.save(detail);
        return mapper.map(updatedDetail, CheckInDetailDTO.class);
    }

    @Override
    public void deleteCheckInDetail(UUID id) {
        log.info("Deleting check-in detail with id: {}", id);
        
        CheckInDetail detail = checkInDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in detail not found with id: " + id));
        
        detail.setDeleted(true);
        checkInDetailRepository.save(detail);
    }
}
