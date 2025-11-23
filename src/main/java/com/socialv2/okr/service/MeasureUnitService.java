package com.socialv2.okr.service;

import com.socialv2.okr.dto.measureUnit.CreateMeasureUnitRequest;
import com.socialv2.okr.dto.measureUnit.MeasureUnitDTO;
import com.socialv2.okr.dto.measureUnit.UpdateMeasureUnitRequest;

import java.util.List;
import java.util.UUID;

public interface MeasureUnitService {
    MeasureUnitDTO createMeasureUnit(CreateMeasureUnitRequest request);
    
    MeasureUnitDTO getMeasureUnitById(UUID id);
    
    List<MeasureUnitDTO> getAllMeasureUnits();
    
    MeasureUnitDTO updateMeasureUnit(UUID id, UpdateMeasureUnitRequest request);
    
    void deleteMeasureUnit(UUID id);
}
