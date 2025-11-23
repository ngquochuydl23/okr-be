package com.socialv2.okr.service;

import com.socialv2.okr.dto.cycle.CreateCycleRequest;
import com.socialv2.okr.dto.cycle.CycleDTO;
import com.socialv2.okr.dto.cycle.UpdateCycleRequest;

import java.util.List;
import java.util.UUID;

public interface CycleService {
    CycleDTO createCycle(CreateCycleRequest request);
    
    CycleDTO getCycleById(UUID id);
    
    List<CycleDTO> getAllCycles();
    
    List<CycleDTO> getCyclesByWorkspaceId(UUID workspaceId);
    
    CycleDTO updateCycle(UUID id, UpdateCycleRequest request);
    
    void deleteCycle(UUID id);
}
