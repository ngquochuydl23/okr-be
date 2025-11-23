package com.socialv2.okr.controller;

import com.socialv2.okr.dto.cycle.CreateCycleRequest;
import com.socialv2.okr.dto.cycle.CycleDTO;
import com.socialv2.okr.dto.cycle.UpdateCycleRequest;
import com.socialv2.okr.service.CycleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("cycles")
@RequiredArgsConstructor
public class CycleController {

    private final CycleService cycleService;

    @PostMapping
    public ResponseEntity<CycleDTO> createCycle(@Valid @RequestBody CreateCycleRequest request) {
        CycleDTO cycle = cycleService.createCycle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cycle);
    }

    @GetMapping("/{cycleId}")
    public ResponseEntity<CycleDTO> getCycleById(@PathVariable UUID cycleId) {
        CycleDTO cycle = cycleService.getCycleById(cycleId);
        return ResponseEntity.ok(cycle);
    }

    @GetMapping
    public ResponseEntity<List<CycleDTO>> getAllCycles() {
        List<CycleDTO> cycles = cycleService.getAllCycles();
        return ResponseEntity.ok(cycles);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<CycleDTO>> getCyclesByWorkspaceId(@PathVariable UUID workspaceId) {
        List<CycleDTO> cycles = cycleService.getCyclesByWorkspaceId(workspaceId);
        return ResponseEntity.ok(cycles);
    }

    @PutMapping("/{cycleId}")
    public ResponseEntity<CycleDTO> updateCycle(
            @PathVariable UUID cycleId,
            @Valid @RequestBody UpdateCycleRequest request) {
        CycleDTO cycle = cycleService.updateCycle(cycleId, request);
        return ResponseEntity.ok(cycle);
    }

    @DeleteMapping("/{cycleId}")
    public ResponseEntity<Void> deleteCycle(@PathVariable UUID cycleId) {
        cycleService.deleteCycle(cycleId);
        return ResponseEntity.noContent().build();
    }
}
