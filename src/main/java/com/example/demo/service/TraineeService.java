package com.example.demo.service;


import com.example.demo.module.Trainee;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class TraineeService {

    public static final List<Trainee> traineeList = new ArrayList<>();

    private static int nextId = 1;

    public TraineeService() {
        if (traineeList.size() == 0) {
            addDefaultData();
        }
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public List<Trainee> addTrainee(Trainee trainee) {
        trainee.setId(getNextId());
        traineeList.add(trainee);
        return traineeList;
    }

    public static void resetTrainee() {
        traineeList.clear();
        nextId = 1;
        addDefaultData();
    }

    private static int getNextId() {
        int id = nextId;
        nextId++;
        return id;
    }

    private static void addDefaultData() {
        try {
            File file = new File("defaultList.txt");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String lineString;
            while ((lineString = bufferedReader.readLine()) != null) {
                Trainee trainee = Trainee.builder().id(getNextId()).name(lineString).build();
                traineeList.add(trainee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
