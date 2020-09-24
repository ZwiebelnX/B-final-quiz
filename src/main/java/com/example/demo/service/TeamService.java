package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.model.exception.TeamNameConflictException;
import com.example.demo.model.exception.TeamNotFoundException;
import com.example.demo.repository.TeamRepo;
import com.example.demo.repository.TraineeRepo;
import com.example.demo.repository.TrainerRepo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

@Service
public class TeamService {

    private final TeamRepo teamRepo;

    private final TraineeRepo traineeRepo;

    private final TrainerRepo trainerRepo;

    private static List<Team> teamList;

    public TeamService(TeamRepo teamRepo, TraineeRepo traineeRepo, TrainerRepo trainerRepo) {
        this.teamRepo = teamRepo;
        this.traineeRepo = traineeRepo;
        this.trainerRepo = trainerRepo;
    }

    @Transactional
    public List<Team> splitIntoTeam() {
        clearTeamInfo();
        teamRepo.deleteAll();

        List<Trainer> trainerList = trainerRepo.findAll();
        Collections.shuffle(trainerList);
        int teamCount = trainerList.size() / 2;

        Iterator<Trainer> trainerIterator = trainerList.listIterator();
        List<Team> teamList = new ArrayList<>();
        for (int teamSequence = 1; teamSequence <= teamCount; teamSequence++) {
            Team team = Team.builder().name("组" + teamSequence).sequence(teamSequence).build();
            teamRepo.save(team);
            team.setTraineeList(new ArrayList<>());
            team.setTrainerList(new ArrayList<>());
            team.getTrainerList().add(trainerIterator.next());
            team.getTrainerList().add(trainerIterator.next());

            teamList.add(team);
        }

        List<Trainee> traineeList = traineeRepo.findAll();
        Collections.shuffle(traineeList);
        Iterator<Trainee> traineeIterator = traineeList.listIterator();
        int teamIndex = 0;
        while (traineeIterator.hasNext() && teamList.size() > 0) {
            Trainee trainee = traineeIterator.next();
            teamList.get(teamIndex).getTraineeList().add(trainee);
            teamIndex = ++teamIndex % teamList.size();
        }

        teamRepo.saveAll(teamList);
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
        return teamRepo.findAll();
    }

    @Transactional
    public void clearTeamInfo() {
        traineeRepo.findAll().forEach((item) -> {
            item.setTeam(null);
            traineeRepo.save(item);
        });
        trainerRepo.findAll().forEach((item) -> {
            item.setTeam(null);
            trainerRepo.save(item);
        });
    }
}
