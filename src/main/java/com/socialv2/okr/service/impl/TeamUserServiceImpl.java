package com.socialv2.okr.service.impl;

import com.socialv2.okr.dto.team.AddTeamUserRequest;
import com.socialv2.okr.dto.team.TeamUserDTO;
import com.socialv2.okr.entities.teams.Team;
import com.socialv2.okr.entities.teams.TeamUser;
import com.socialv2.okr.entities.users.User;
import com.socialv2.okr.repository.team.TeamRepository;
import com.socialv2.okr.repository.team.TeamUserRepository;
import com.socialv2.okr.repository.user.UserRepository;
import com.socialv2.okr.service.TeamUserService;
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
public class TeamUserServiceImpl implements TeamUserService {

    private final TeamUserRepository teamUserRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public TeamUserDTO addUserToTeam(AddTeamUserRequest request) {
        log.info("Adding user {} to team {}", request.getUserId(), request.getTeamId());
        
        // Check if already exists
        teamUserRepository.findByUserIdAndTeamId(request.getUserId(), request.getTeamId())
                .ifPresent(tu -> {
                    throw new RuntimeException("User already belongs to this team");
                });
        
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + request.getUserId()));
        
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found with id: " + request.getTeamId()));
        
        TeamUser teamUser = TeamUser.builder()
                .user(user)
                .team(team)
                .build();
        
        TeamUser savedTeamUser = teamUserRepository.save(teamUser);
        TeamUserDTO dto = mapper.map(savedTeamUser, TeamUserDTO.class);
        dto.setTeamId(team.getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public TeamUserDTO getTeamUserById(UUID id) {
        log.info("Fetching team user with id: {}", id);
        TeamUser teamUser = teamUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team user not found with id: " + id));
        
        TeamUserDTO dto = mapper.map(teamUser, TeamUserDTO.class);
        dto.setTeamId(teamUser.getTeam().getId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamUserDTO> getAllTeamUsers() {
        log.info("Fetching all team users");
        return teamUserRepository.findAll().stream()
                .map(teamUser -> {
                    TeamUserDTO dto = mapper.map(teamUser, TeamUserDTO.class);
                    dto.setTeamId(teamUser.getTeam().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamUserDTO> getTeamUsersByTeamId(UUID teamId) {
        log.info("Fetching team users for team: {}", teamId);
        return teamUserRepository.findByTeamId(teamId).stream()
                .map(teamUser -> {
                    TeamUserDTO dto = mapper.map(teamUser, TeamUserDTO.class);
                    dto.setTeamId(teamUser.getTeam().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeamUserDTO> getTeamUsersByUserId(UUID userId) {
        log.info("Fetching team users for user: {}", userId);
        return teamUserRepository.findByUserId(userId).stream()
                .map(teamUser -> {
                    TeamUserDTO dto = mapper.map(teamUser, TeamUserDTO.class);
                    dto.setTeamId(teamUser.getTeam().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void removeUserFromTeam(UUID id) {
        log.info("Removing team user with id: {}", id);
        
        TeamUser teamUser = teamUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team user not found with id: " + id));
        
        teamUser.setDeleted(true);
        teamUserRepository.save(teamUser);
    }

    @Override
    public void removeUserFromTeamByUserAndTeam(UUID userId, UUID teamId) {
        log.info("Removing user {} from team {}", userId, teamId);
        
        TeamUser teamUser = teamUserRepository.findByUserIdAndTeamId(userId, teamId)
                .orElseThrow(() -> new RuntimeException("Team user relationship not found"));
        
        teamUser.setDeleted(true);
        teamUserRepository.save(teamUser);
    }
}
