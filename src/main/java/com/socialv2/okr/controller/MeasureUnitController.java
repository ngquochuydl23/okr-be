package com.socialv2.okr.controller;

import com.socialv2.okr.dto.measureUnit.CreateMeasureUnitRequest;
import com.socialv2.okr.dto.measureUnit.MeasureUnitDTO;
import com.socialv2.okr.dto.measureUnit.UpdateMeasureUnitRequest;
import com.socialv2.okr.service.MeasureUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("measure-units")
@RequiredArgsConstructor
public class MeasureUnitController {

    private final MeasureUnitService measureUnitService;

    @PostMapping
    public ResponseEntity<MeasureUnitDTO> createMeasureUnit(@Valid @RequestBody CreateMeasureUnitRequest request) {
        MeasureUnitDTO measureUnit = measureUnitService.createMeasureUnit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(measureUnit);
    }

    @GetMapping("/{measureUnitId}")
    public ResponseEntity<MeasureUnitDTO> getMeasureUnitById(@PathVariable UUID measureUnitId) {
        MeasureUnitDTO measureUnit = measureUnitService.getMeasureUnitById(measureUnitId);
        return ResponseEntity.ok(measureUnit);
    }

    @GetMapping
    public ResponseEntity<List<MeasureUnitDTO>> getAllMeasureUnits() {
        List<MeasureUnitDTO> measureUnits = measureUnitService.getAllMeasureUnits();
        return ResponseEntity.ok(measureUnits);
    }

    @PutMapping("/{measureUnitId}")
    public ResponseEntity<MeasureUnitDTO> updateMeasureUnit(
            @PathVariable UUID measureUnitId,
            @Valid @RequestBody UpdateMeasureUnitRequest request) {
        MeasureUnitDTO measureUnit = measureUnitService.updateMeasureUnit(measureUnitId, request);
        return ResponseEntity.ok(measureUnit);
    }

    @DeleteMapping("/{measureUnitId}")
    public ResponseEntity<Void> deleteMeasureUnit(@PathVariable UUID measureUnitId) {
        measureUnitService.deleteMeasureUnit(measureUnitId);
        return ResponseEntity.noContent().build();
    }
}
