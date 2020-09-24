package com.example.demo.repository;

import com.example.demo.model.Team;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepo extends CrudRepository<Team, Long> {

    List<Team> findAll();
}
