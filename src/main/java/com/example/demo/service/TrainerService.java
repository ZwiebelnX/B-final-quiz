package com.example.demo.service;

import com.example.demo.model.Trainer;
import com.example.demo.repository.TrainerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class TrainerService {

    private final TrainerRepo trainerRepo;

    public TrainerService(TrainerRepo trainerRepo) {
        this.trainerRepo = trainerRepo;
    }

    @PostConstruct
    @Transactional
    public void addDefaultTrainerData() {
        if (trainerRepo.count() == 0) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                File file = new File("trainer.json");
                List<Trainer> trainerList = Arrays.asList(objectMapper.readValue(file, Trainer[].class));
                trainerRepo.saveAll(trainerList);
            } catch (Exception e) {
                System.out.println("添加默认讲师数据失败！");
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public Trainer addTrainer(Trainer trainer) {
        return trainerRepo.save(trainer);
    }
}
