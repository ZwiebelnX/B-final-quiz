package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private long id;

    @NotEmpty(message = "名称不能为空")
    @Length(max = 64, message = "名称长度需小于64")
    @Column(length = 64, nullable = false)
    private String name;

    @ManyToOne
    @JsonIgnore
    private Team team;

    @NotEmpty(message = "办公室不能为空")
    @Length(max = 64, message = "办公室名称长度需小于64")
    @Column(length = 64, nullable = false)
    private String office;

    @NotEmpty(message = "电子邮箱不能为空")
    @Email(message = "电子邮箱格式不合法")
    @Column(length = 64, nullable = false)
    private String email;

    @NotEmpty(message = "github账号不能为空")
    @Length(max = 64, message = "github账号长度需小于64")
    @Column(length = 64, nullable = false)
    private String github;

    @NotEmpty(message = "zoomId不能为空")
    @Length(max = 64, message = "zoomId长度需小于64")
    @Column(length = 64, nullable = false)
    private String zoomId;
}
