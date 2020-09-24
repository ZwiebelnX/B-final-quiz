package com.example.demo.controller;


import com.example.demo.model.Trainee;
import com.example.demo.service.TraineeService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/trainees", produces = "application/json;charset=UTF-8")
@CrossOrigin
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping("/ungrouped")
    @ResponseStatus(HttpStatus.OK)
    public List<Trainee> getUngroupedTraineeList() {
        return traineeService.getUngroupedList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trainee addTrainee(@Valid @RequestBody Trainee trainee) {
        return traineeService.addTrainee(trainee);
    }

    @DeleteMapping("/{traineeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainee(@PathVariable long traineeId) {
        traineeService.deleteTrainee(traineeId);

    }

}
