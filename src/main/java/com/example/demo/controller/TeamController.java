package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.model.exception.TeamNameConflictException;
import com.example.demo.model.exception.TeamNotFoundException;
import com.example.demo.service.TeamService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("")
    public ResponseEntity<List<Team>> splitIntoTeam() {
        return ResponseEntity.ok(teamService.splitIntoTeam());
    }

    @PostMapping("/{index}/name")
    public ResponseEntity<Void> changeTeamName(@PathVariable int index, @RequestBody Team team)
        throws TeamNameConflictException, TeamNotFoundException {
        teamService.changeTeamName(index, team);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Team>> getTeamList() {
        return ResponseEntity.ok(teamService.getTeamList());
    }
}
