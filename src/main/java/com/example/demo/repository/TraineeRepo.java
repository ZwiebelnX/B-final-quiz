package com.example.demo.repository;

import com.example.demo.model.Trainee;

import org.springframework.data.repository.CrudRepository;

public interface TraineeRepo extends CrudRepository<Trainee, Long> {
}
