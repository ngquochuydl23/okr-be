package com.socialv2.okr.service;

import com.socialv2.okr.dto.team.AddTeamUserRequest;
import com.socialv2.okr.dto.team.TeamUserDTO;

import java.util.List;
import java.util.UUID;

public interface TeamUserService {
    TeamUserDTO addUserToTeam(AddTeamUserRequest request);
    
    TeamUserDTO getTeamUserById(UUID id);
    
    List<TeamUserDTO> getAllTeamUsers();
    
    List<TeamUserDTO> getTeamUsersByTeamId(UUID teamId);
    
    List<TeamUserDTO> getTeamUsersByUserId(UUID userId);
    
    void removeUserFromTeam(UUID id);
    
    void removeUserFromTeamByUserAndTeam(UUID userId, UUID teamId);
}
