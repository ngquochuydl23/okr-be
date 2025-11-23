package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.workspace.WorkspaceDTO;
import com.socialv2.okr.entities.workspaces.Workspace;
import com.socialv2.okr.repository.workspace.WorkspaceRepository;
import com.socialv2.okr.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    private final ModelMapper mapper;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public WorkspaceDTO createOrUpdateWorkspace(Workspace workspace) {
        return mapper.map(workspaceRepository.save(workspace), WorkspaceDTO.class);
    }

    @Override
    public WorkspaceDTO getWorkspaceById(String id) {
        return mapper.map(workspaceRepository.findWorkspaceById(UUID.fromString(id)), WorkspaceDTO.class);
    }
}
