package com.socialv2.okr.controller;

import com.socialv2.okr.dto.team.AddTeamUserRequest;
import com.socialv2.okr.dto.team.CreateTeamRequest;
import com.socialv2.okr.dto.team.TeamDTO;
import com.socialv2.okr.dto.team.TeamUserDTO;
import com.socialv2.okr.dto.team.UpdateTeamRequest;
import com.socialv2.okr.service.TeamService;
import com.socialv2.okr.service.TeamUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamUserService teamUserService;

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody CreateTeamRequest request) {
        TeamDTO team = teamService.createTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(team);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable UUID teamId) {
        TeamDTO team = teamService.getTeamById(teamId);
        return ResponseEntity.ok(team);
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/workspace/{workspaceId}")
    public ResponseEntity<List<TeamDTO>> getTeamsByWorkspaceId(@PathVariable UUID workspaceId) {
        List<TeamDTO> teams = teamService.getTeamsByWorkspaceId(workspaceId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{teamId}/sub-teams")
    public ResponseEntity<List<TeamDTO>> getSubTeams(@PathVariable UUID teamId) {
        List<TeamDTO> subTeams = teamService.getSubTeams(teamId);
        return ResponseEntity.ok(subTeams);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamDTO> updateTeam(
            @PathVariable UUID teamId,
            @Valid @RequestBody UpdateTeamRequest request) {
        TeamDTO team = teamService.updateTeam(teamId, request);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable UUID teamId) {
        teamService.deleteTeam(teamId);
        return ResponseEntity.noContent().build();
    }

    // Team User endpoints
    @PostMapping("/users")
    public ResponseEntity<TeamUserDTO> addUserToTeam(@Valid @RequestBody AddTeamUserRequest request) {
        TeamUserDTO teamUser = teamUserService.addUserToTeam(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamUser);
    }

    @GetMapping("/{teamId}/users")
    public ResponseEntity<List<TeamUserDTO>> getTeamUsers(@PathVariable UUID teamId) {
        List<TeamUserDTO> teamUsers = teamUserService.getTeamUsersByTeamId(teamId);
        return ResponseEntity.ok(teamUsers);
    }

    @DeleteMapping("/users/{teamUserId}")
    public ResponseEntity<Void> removeUserFromTeam(@PathVariable UUID teamUserId) {
        teamUserService.removeUserFromTeam(teamUserId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{teamId}/users/{userId}")
    public ResponseEntity<Void> removeUserFromTeamByIds(
            @PathVariable UUID teamId,
            @PathVariable UUID userId) {
        teamUserService.removeUserFromTeamByUserAndTeam(userId, teamId);
        return ResponseEntity.noContent().build();
    }
}
