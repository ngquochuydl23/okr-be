package com.socialv2.okr.service;

import com.socialv2.okr.dto.team.CreateTeamRequest;
import com.socialv2.okr.dto.team.TeamDTO;
import com.socialv2.okr.dto.team.UpdateTeamRequest;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    TeamDTO createTeam(CreateTeamRequest request);
    
    TeamDTO getTeamById(UUID id);
    
    List<TeamDTO> getAllTeams();
    
    List<TeamDTO> getTeamsByWorkspaceId(UUID workspaceId);
    
    List<TeamDTO> getSubTeams(UUID parentTeamId);
    
    TeamDTO updateTeam(UUID id, UpdateTeamRequest request);
    
    void deleteTeam(UUID id);
}
