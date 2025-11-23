package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.cycle.CreateCycleRequest;
import com.socialv2.okr.dto.cycle.CycleDTO;
import com.socialv2.okr.dto.cycle.UpdateCycleRequest;
import com.socialv2.okr.entities.cycles.Cycle;
import com.socialv2.okr.entities.workspaces.Workspace;
import com.socialv2.okr.repository.cycle.CycleRepository;
import com.socialv2.okr.repository.workspace.WorkspaceRepository;
import com.socialv2.okr.service.CycleService;
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
public class CycleServiceImpl implements CycleService {

    private final CycleRepository cycleRepository;
    private final WorkspaceRepository workspaceRepository;
    private final ModelMapper mapper;

    @Override
    public CycleDTO createCycle(CreateCycleRequest request) {
        log.info("Creating new cycle with name: {}", request.getName());
        
        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                .orElseThrow(() -> new RuntimeException("Workspace not found with id: " + request.getWorkspaceId()));
        
        Cycle cycle = Cycle.builder()
                .name(request.getName())
                .description(request.getDescription())
                .workspace(workspace)
                .build();
        
        Cycle savedCycle = cycleRepository.save(cycle);
        return mapper.map(savedCycle, CycleDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public CycleDTO getCycleById(UUID id) {
        log.info("Fetching cycle with id: {}", id);
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cycle not found with id: " + id));
        return mapper.map(cycle, CycleDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CycleDTO> getAllCycles() {
        log.info("Fetching all cycles");
        return cycleRepository.findAll().stream()
                .map(cycle -> mapper.map(cycle, CycleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CycleDTO> getCyclesByWorkspaceId(UUID workspaceId) {
        log.info("Fetching cycles for workspace: {}", workspaceId);
        return cycleRepository.findByWorkspaceId(workspaceId).stream()
                .map(cycle -> mapper.map(cycle, CycleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CycleDTO updateCycle(UUID id, UpdateCycleRequest request) {
        log.info("Updating cycle with id: {}", id);
        
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cycle not found with id: " + id));
        
        if (request.getName() != null) {
            cycle.setName(request.getName());
        }
        if (request.getDescription() != null) {
            cycle.setDescription(request.getDescription());
        }
        
        Cycle updatedCycle = cycleRepository.save(cycle);
        return mapper.map(updatedCycle, CycleDTO.class);
    }

    @Override
    public void deleteCycle(UUID id) {
        log.info("Deleting cycle with id: {}", id);
        
        Cycle cycle = cycleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cycle not found with id: " + id));
        
        cycle.setDeleted(true);
        cycleRepository.save(cycle);
    }
}
