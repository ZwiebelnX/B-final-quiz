package com.example.demo.repository;

import com.example.demo.model.Trainer;

import org.springframework.data.repository.CrudRepository;

public interface TrainerRepo extends CrudRepository<Trainer, Long> {

}
