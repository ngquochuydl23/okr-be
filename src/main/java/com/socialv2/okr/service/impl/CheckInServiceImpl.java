package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.checkin.CheckInDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInDetailRequest;
import com.socialv2.okr.dto.checkin.CreateCheckInRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInRequest;
import com.socialv2.okr.entities.checkin.CheckIn;
import com.socialv2.okr.entities.checkin.CheckInDetail;
import com.socialv2.okr.entities.keyResults.KeyResult;
import com.socialv2.okr.entities.objectives.Objective;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.repository.checkin.CheckInRepository;
import com.socialv2.okr.repository.objective.ObjectiveRepository;
import com.socialv2.okr.repository.objective.KeyResultRepository;
import com.socialv2.okr.repository.user.UserRepository;
import com.socialv2.okr.service.CheckInService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final ObjectiveRepository objectiveRepository;
    private final UserRepository userRepository;
    private final KeyResultRepository keyResultRepository;
    private final ModelMapper mapper;

    @Override
    public CheckInDTO createCheckIn(CreateCheckInRequest request) {
        log.info("Creating new check-in for objective: {}", request.getObjectiveId());
        
        Objective objective = objectiveRepository.findById(request.getObjectiveId())
                .orElseThrow(() -> new RuntimeException("Objective not found with id: " + request.getObjectiveId()));
        
        User reporter = userRepository.findById(request.getReporterId())
                .orElseThrow(() -> new RuntimeException("Reporter not found with id: " + request.getReporterId()));
        
        CheckIn checkIn = CheckIn.builder()
                .objective(objective)
                .reporter(reporter)
                .checkInDetails(new HashSet<>())
                .build();
        
        // Create check-in details if provided
        if (request.getCheckInDetails() != null && !request.getCheckInDetails().isEmpty()) {
            Set<CheckInDetail> details = new HashSet<>();
            for (CreateCheckInDetailRequest detailRequest : request.getCheckInDetails()) {
                KeyResult keyResult = keyResultRepository.findById(detailRequest.getKeyResultId())
                        .orElseThrow(() -> new RuntimeException("Key result not found with id: " + detailRequest.getKeyResultId()));
                
                CheckInDetail detail = CheckInDetail.builder()
                        .change(detailRequest.getChange())
                        .progress(detailRequest.getProgress())
                        .obtainedValue(detailRequest.getObtainedValue())
                        .confidenceLevel(detailRequest.getConfidenceLevel())
                        .keyResult(keyResult)
                        .checkIn(checkIn)
                        .build();
                details.add(detail);
            }
            checkIn.setCheckInDetails(details);
        }
        
        CheckIn savedCheckIn = checkInRepository.save(checkIn);
        return mapper.map(savedCheckIn, CheckInDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CheckInDTO getCheckInById(UUID id) {
        log.info("Fetching check-in with id: {}", id);
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in not found with id: " + id));
        
        return mapper.map(checkIn, CheckInDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDTO> getAllCheckIns() {
        log.info("Fetching all check-ins");
        return checkInRepository.findAll().stream()
                .map(checkIn -> mapper.map(checkIn, CheckInDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDTO> getCheckInsByObjectiveId(UUID objectiveId) {
        log.info("Fetching check-ins for objective: {}", objectiveId);
        return checkInRepository.findByObjectiveId(objectiveId).stream()
                .map(checkIn -> mapper.map(checkIn, CheckInDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CheckInDTO> getCheckInsByReporterId(UUID reporterId) {
        log.info("Fetching check-ins for reporter: {}", reporterId);
        return checkInRepository.findByReporterId(reporterId).stream()
                .map(checkIn -> mapper.map(checkIn, CheckInDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CheckInDTO updateCheckIn(UUID id, UpdateCheckInRequest request) {
        log.info("Updating check-in with id: {}", id);
        
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in not found with id: " + id));
        
        if (request.getObjectiveId() != null) {
            Objective objective = objectiveRepository.findById(request.getObjectiveId())
                    .orElseThrow(() -> new RuntimeException("Objective not found with id: " + request.getObjectiveId()));
            checkIn.setObjective(objective);
        }
        
        if (request.getReporterId() != null) {
            User reporter = userRepository.findById(request.getReporterId())
                    .orElseThrow(() -> new RuntimeException("Reporter not found with id: " + request.getReporterId()));
            checkIn.setReporter(reporter);
        }
        
        CheckIn updatedCheckIn = checkInRepository.save(checkIn);
        return mapper.map(updatedCheckIn, CheckInDTO.class);
    }

    @Override
    public void deleteCheckIn(UUID id) {
        log.info("Deleting check-in with id: {}", id);
        
        CheckIn checkIn = checkInRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Check-in not found with id: " + id));
        
        checkIn.setDeleted(true);
        checkInRepository.save(checkIn);
    }
}
