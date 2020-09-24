package com.example.demo.repository;

import com.example.demo.model.Trainer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainerRepo extends CrudRepository<Trainer, Long> {

    List<Trainer> findAllByTeamNull();
}
