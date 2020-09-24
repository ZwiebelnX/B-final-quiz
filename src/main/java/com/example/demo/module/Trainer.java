package com.example.demo.module;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Trainer {

    @Id
    @GeneratedValue
    private long id;


    @NotEmpty(message = "讲师名称不能为空")
    @Length(max = 64, message = "讲师名称长度需小于64")
    @Column(length = 64, nullable = false)
    private String name;
}
