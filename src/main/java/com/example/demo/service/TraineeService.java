package com.example.demo.service;


import com.example.demo.model.Trainee;
import com.example.demo.repository.TraineeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class TraineeService {

    public static List<Trainee> traineeList = new ArrayList<>();

    private final TraineeRepo traineeRepo;

    public TraineeService(TraineeRepo traineeRepo) {
        this.traineeRepo = traineeRepo;
    }

    public List<Trainee> getUngroupedList() {
        return traineeRepo.findAllByTeamNull();
    }

    @Transactional
    public Trainee addTrainee(Trainee trainee) {
        traineeRepo.save(trainee);
        return trainee;
    }



    @PostConstruct
    @Transactional
    public void addDefaultTraineeData() {
        if (traineeRepo.count() == 0) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File("trainee.json");
                List<Trainee> traineeList = Arrays.asList(objectMapper.readValue(file, Trainee[].class));
                traineeRepo.saveAll(traineeList);
            } catch (Exception e) {
                System.out.println("添加默认学员数据失败！");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

}
