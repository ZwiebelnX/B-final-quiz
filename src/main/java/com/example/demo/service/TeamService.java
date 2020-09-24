package com.example.demo.service;


import com.example.demo.module.Team;
import com.example.demo.module.Trainee;
import com.example.demo.module.exception.TeamNameConflictException;
import com.example.demo.module.exception.TeamNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    public static final List<Team> teamList = new ArrayList<>();

    public List<Team> splitIntoTeam() {
        if (teamList.size() < 6) {
            teamList.clear();
            for (int i = 0; i < 6; i++) {
                teamList.add(Team.builder().name("Team " + (i + 1)).traineeList(new ArrayList<>()).build());
            }
        } else {
            for (Team team : teamList) {
                team.getTraineeList().clear();
            }
        }

        List<Trainee> traineeList = new ArrayList<>(TraineeService.traineeList);
        int teamIndex = 0;
        while (traineeList.size() > 0) {
            int selectId = (int) Math.round(Math.random() * (traineeList.size() - 1));
            teamList.get(teamIndex).getTraineeList().add(traineeList.get(selectId));
            traineeList.remove(selectId);
            if (teamIndex >= 5) {
                teamIndex = 0;
            } else {
                teamIndex++;
            }
        }

        return teamList;
    }

    public void changeTeamName(int index, Team team) throws TeamNotFoundException, TeamNameConflictException {
        Team selectedTeam;
        try {
            selectedTeam = teamList.get(index);
        } catch (Exception e) {
            throw new TeamNotFoundException();
        }

        for (Team currentTeam : teamList) {
            if (currentTeam.getName().equals(team.getName())) {
                throw new TeamNameConflictException();
            }
        }

        selectedTeam.setName(team.getName());
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public static void resetTeam() {
        teamList.clear();
        for (int i = 0; i < 6; i++) {
            teamList.add(Team.builder().name("Team " + (i + 1)).traineeList(new ArrayList<>()).build());
        }
    }
}
