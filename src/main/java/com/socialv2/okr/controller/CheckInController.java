package com.socialv2.okr.controller;

import com.socialv2.okr.dto.checkin.CheckInDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInRequest;
import com.socialv2.okr.service.CheckInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("check-ins")
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService checkInService;

    @PostMapping
    public ResponseEntity<CheckInDTO> createCheckIn(@Valid @RequestBody CreateCheckInRequest request) {
        CheckInDTO checkIn = checkInService.createCheckIn(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkIn);
    }

    @GetMapping("/{checkInId}")
    public ResponseEntity<CheckInDTO> getCheckInById(@PathVariable UUID checkInId) {
        CheckInDTO checkIn = checkInService.getCheckInById(checkInId);
        return ResponseEntity.ok(checkIn);
    }

    @GetMapping
    public ResponseEntity<List<CheckInDTO>> getAllCheckIns() {
        List<CheckInDTO> checkIns = checkInService.getAllCheckIns();
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/objective/{objectiveId}")
    public ResponseEntity<List<CheckInDTO>> getCheckInsByObjectiveId(@PathVariable UUID objectiveId) {
        List<CheckInDTO> checkIns = checkInService.getCheckInsByObjectiveId(objectiveId);
        return ResponseEntity.ok(checkIns);
    }

    @GetMapping("/reporter/{reporterId}")
    public ResponseEntity<List<CheckInDTO>> getCheckInsByReporterId(@PathVariable UUID reporterId) {
        List<CheckInDTO> checkIns = checkInService.getCheckInsByReporterId(reporterId);
        return ResponseEntity.ok(checkIns);
    }

    @PutMapping("/{checkInId}")
    public ResponseEntity<CheckInDTO> updateCheckIn(
            @PathVariable UUID checkInId,
            @Valid @RequestBody UpdateCheckInRequest request) {
        CheckInDTO checkIn = checkInService.updateCheckIn(checkInId, request);
        return ResponseEntity.ok(checkIn);
    }

    @DeleteMapping("/{checkInId}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable UUID checkInId) {
        checkInService.deleteCheckIn(checkInId);
        return ResponseEntity.noContent().build();
    }
}
