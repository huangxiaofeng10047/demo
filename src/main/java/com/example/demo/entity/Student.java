package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hxf
 * @date 2023年05月18日 14:16
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;
    @Column(name = "social_security_number")
    private Integer socialSecurityNumber;
    private String name;
    private String surname;
    private String address;
    private Integer age;
    @ManyToOne
    @JoinColumn(name = "career_id")
    private Career career;
    @ManyToMany(mappedBy = "students")
    @JsonIgnoreProperties("students")
    private List<Subject> subjects=new ArrayList<>();
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Score>scores=new ArrayList<>();
}
