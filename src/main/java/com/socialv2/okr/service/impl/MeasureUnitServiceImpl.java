package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.measureUnit.CreateMeasureUnitRequest;
import com.socialv2.okr.dto.measureUnit.MeasureUnitDTO;
import com.socialv2.okr.dto.measureUnit.UpdateMeasureUnitRequest;
import com.socialv2.okr.entities.measureUnits.MeasureUnit;
import com.socialv2.okr.repository.measureUnit.MeasureUnitRepository;
import com.socialv2.okr.service.MeasureUnitService;
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
public class MeasureUnitServiceImpl implements MeasureUnitService {

    private final MeasureUnitRepository measureUnitRepository;
    private final ModelMapper mapper;

    @Override
    public MeasureUnitDTO createMeasureUnit(CreateMeasureUnitRequest request) {
        log.info("Creating new measure unit with name: {}", request.getName());
        
        MeasureUnit measureUnit = MeasureUnit.builder()
                .name(request.getName())
                .isDecimal(request.isDecimal())
                .startValue(request.getStartValue())
                .targetValue(request.getTargetValue())
                .build();
        
        MeasureUnit savedMeasureUnit = measureUnitRepository.save(measureUnit);
        return mapper.map(savedMeasureUnit, MeasureUnitDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public MeasureUnitDTO getMeasureUnitById(UUID id) {
        log.info("Fetching measure unit with id: {}", id);
        MeasureUnit measureUnit = measureUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Measure unit not found with id: " + id));
        return mapper.map(measureUnit, MeasureUnitDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MeasureUnitDTO> getAllMeasureUnits() {
        log.info("Fetching all measure units");
        return measureUnitRepository.findAll().stream()
                .map(measureUnit -> mapper.map(measureUnit, MeasureUnitDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MeasureUnitDTO updateMeasureUnit(UUID id, UpdateMeasureUnitRequest request) {
        log.info("Updating measure unit with id: {}", id);
        
        MeasureUnit measureUnit = measureUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Measure unit not found with id: " + id));
        
        if (request.getName() != null) {
            measureUnit.setName(request.getName());
        }
        if (request.getIsDecimal() != null) {
            measureUnit.setDecimal(request.getIsDecimal());
        }
        if (request.getStartValue() != null) {
            measureUnit.setStartValue(request.getStartValue());
        }
        if (request.getTargetValue() != null) {
            measureUnit.setTargetValue(request.getTargetValue());
        }
        
        MeasureUnit updatedMeasureUnit = measureUnitRepository.save(measureUnit);
        return mapper.map(updatedMeasureUnit, MeasureUnitDTO.class);
    }

    @Override
    public void deleteMeasureUnit(UUID id) {
        log.info("Deleting measure unit with id: {}", id);
        
        MeasureUnit measureUnit = measureUnitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Measure unit not found with id: " + id));
        
        measureUnit.setDeleted(true);
        measureUnitRepository.save(measureUnit);
    }
}
