package com.socialv2.okr.controller;

import com.socialv2.okr.dto.checkin.CheckInDetailDTO;
import com.socialv2.okr.dto.checkin.CreateCheckInDetailRequest;
import com.socialv2.okr.dto.checkin.UpdateCheckInDetailRequest;
import com.socialv2.okr.service.CheckInDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("check-in-details")
@RequiredArgsConstructor
public class CheckInDetailController {

    private final CheckInDetailService checkInDetailService;

    @PostMapping("/check-in/{checkInId}")
    public ResponseEntity<CheckInDetailDTO> createCheckInDetail(
            @PathVariable UUID checkInId,
            @Valid @RequestBody CreateCheckInDetailRequest request) {
        CheckInDetailDTO detail = checkInDetailService.createCheckInDetail(checkInId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(detail);
    }

    @GetMapping("/{detailId}")
    public ResponseEntity<CheckInDetailDTO> getCheckInDetailById(@PathVariable UUID detailId) {
        CheckInDetailDTO detail = checkInDetailService.getCheckInDetailById(detailId);
        return ResponseEntity.ok(detail);
    }

    @GetMapping
    public ResponseEntity<List<CheckInDetailDTO>> getAllCheckInDetails() {
        List<CheckInDetailDTO> details = checkInDetailService.getAllCheckInDetails();
        return ResponseEntity.ok(details);
    }

    @GetMapping("/check-in/{checkInId}")
    public ResponseEntity<List<CheckInDetailDTO>> getCheckInDetailsByCheckInId(@PathVariable UUID checkInId) {
        List<CheckInDetailDTO> details = checkInDetailService.getCheckInDetailsByCheckInId(checkInId);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/key-result/{keyResultId}")
    public ResponseEntity<List<CheckInDetailDTO>> getCheckInDetailsByKeyResultId(@PathVariable UUID keyResultId) {
        List<CheckInDetailDTO> details = checkInDetailService.getCheckInDetailsByKeyResultId(keyResultId);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/{detailId}")
    public ResponseEntity<CheckInDetailDTO> updateCheckInDetail(
            @PathVariable UUID detailId,
            @Valid @RequestBody UpdateCheckInDetailRequest request) {
        CheckInDetailDTO detail = checkInDetailService.updateCheckInDetail(detailId, request);
        return ResponseEntity.ok(detail);
    }

    @DeleteMapping("/{detailId}")
    public ResponseEntity<Void> deleteCheckInDetail(@PathVariable UUID detailId) {
        checkInDetailService.deleteCheckInDetail(detailId);
        return ResponseEntity.noContent().build();
    }
}
