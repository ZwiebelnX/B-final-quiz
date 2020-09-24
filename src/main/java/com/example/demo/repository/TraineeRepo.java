package com.example.demo.repository;

import com.example.demo.model.Trainee;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TraineeRepo extends CrudRepository<Trainee, Long> {
    List<Trainee> findAllByTeamNull();

    List<Trainee> findAll();
}
