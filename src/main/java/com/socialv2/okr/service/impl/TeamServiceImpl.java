package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.team.CreateTeamRequest;
import com.socialv2.okr.dto.team.TeamDTO;
import com.socialv2.okr.dto.team.UpdateTeamRequest;
import com.socialv2.okr.entities.teams.Team;
import com.socialv2.okr.entities.workspaces.Workspace;
import com.socialv2.okr.repository.team.TeamRepository;
import com.socialv2.okr.repository.workspace.WorkspaceRepository;
import com.socialv2.okr.service.TeamService;
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
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final WorkspaceRepository workspaceRepository;
    private final ModelMapper mapper;

    @Override
    public TeamDTO createTeam(CreateTeamRequest request) {
        log.info("Creating new team with name: {}", request.getName());
        
        Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
                .orElseThrow(() -> new RuntimeException("Workspace not found with id: " + request.getWorkspaceId()));
        
        Team.TeamBuilder teamBuilder = Team.builder()
                .name(request.getName())
                .workspace(workspace);
        
        if (request.getParentTeamId() != null) {
            Team parentTeam = teamRepository.findById(request.getParentTeamId())
                    .orElseThrow(() -> new RuntimeException("Parent team not found with id: " + request.getParentTeamId()));
            teamBuilder.parentTeam(parentTeam);
        }
        
        Team team = teamBuilder.build();
        Team savedTeam = teamRepository.save(team);
        
        TeamDTO dto = mapper.map(savedTeam, TeamDTO.class);
        if (savedTeam.getParentTeam() != null) {
            dto.setParentTeamId(savedTeam.getParentTeam().getId());
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public TeamDTO getTeamById(UUID id) {
        log.info("Fetching team with id: {}", id);
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
        
        TeamDTO dto = mapper.map(team, TeamDTO.class);
        if (team.getParentTeam() != null) {
            dto.setParentTeamId(team.getParentTeam().getId());
        }
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> getAllTeams() {
        log.info("Fetching all teams");
        return teamRepository.findAll().stream()
                .map(team -> {
                    TeamDTO dto = mapper.map(team, TeamDTO.class);
                    if (team.getParentTeam() != null) {
                        dto.setParentTeamId(team.getParentTeam().getId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> getTeamsByWorkspaceId(UUID workspaceId) {
        log.info("Fetching teams for workspace: {}", workspaceId);
        return teamRepository.findAll().stream()
                .filter(team -> team.getWorkspace() != null && team.getWorkspace().getId().equals(workspaceId))
                .map(team -> {
                    TeamDTO dto = mapper.map(team, TeamDTO.class);
                    if (team.getParentTeam() != null) {
                        dto.setParentTeamId(team.getParentTeam().getId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamDTO> getSubTeams(UUID parentTeamId) {
        log.info("Fetching sub-teams for parent team: {}", parentTeamId);
        return teamRepository.findAll().stream()
                .filter(team -> team.getParentTeam() != null && team.getParentTeam().getId().equals(parentTeamId))
                .map(team -> {
                    TeamDTO dto = mapper.map(team, TeamDTO.class);
                    dto.setParentTeamId(team.getParentTeam().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO updateTeam(UUID id, UpdateTeamRequest request) {
        log.info("Updating team with id: {}", id);
        
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
        
        if (request.getName() != null) {
            team.setName(request.getName());
        }
        
        if (request.getParentTeamId() != null) {
            Team parentTeam = teamRepository.findById(request.getParentTeamId())
                    .orElseThrow(() -> new RuntimeException("Parent team not found with id: " + request.getParentTeamId()));
            team.setParentTeam(parentTeam);
        }
        
        Team updatedTeam = teamRepository.save(team);
        TeamDTO dto = mapper.map(updatedTeam, TeamDTO.class);
        if (updatedTeam.getParentTeam() != null) {
            dto.setParentTeamId(updatedTeam.getParentTeam().getId());
        }
        return dto;
    }

    @Override
    public void deleteTeam(UUID id) {
        log.info("Deleting team with id: {}", id);
        
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
        
        team.setDeleted(true);
        teamRepository.save(team);
    }
}
