package com.example.demo.controller;


import com.example.demo.module.Trainee;
import com.example.demo.service.TraineeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/trainee")
@CrossOrigin
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Trainee>> getTraineeList() {
        return ResponseEntity.ok(traineeService.getTraineeList());
    }

    @PostMapping("")
    public ResponseEntity<List<Trainee>> addTrainee(@RequestBody Trainee trainee) {
        return ResponseEntity.created(URI.create("")).body(traineeService.addTrainee(trainee));
    }

}
