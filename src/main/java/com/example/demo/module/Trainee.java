package com.example.demo.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Trainee {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @NotEmpty
    @Length(max = 64)
    @Column(length = 64, nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    private Team team;

    @NotEmpty
    @Length(max = 64)
    @Column(length = 64, nullable = false)
    private String office;

    @NotEmpty
    @Email
    @Column(length = 64, nullable = false)
    private String email;

    @NotEmpty
    @Length(max = 64)
    @Column(length = 64, nullable = false)
    private String github;

    @NotEmpty
    @Length(max = 64)
    @Column(length = 64, nullable = false)
    private String zoomId;
}
