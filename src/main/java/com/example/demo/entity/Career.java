package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxf
 * @date 2023年05月18日 14:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "career_id")
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "career_subject",
            joinColumns = @JoinColumn(name = "career_id", referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "subject_id"))
    private List<Subject> subjects=new ArrayList<>();
    @OneToMany(mappedBy = "career")
    private List<Student>students=new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "career_teacher",
            joinColumns = @JoinColumn(name = "career_id", referencedColumnName = "career_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "teacher_id"))
    @JsonIgnoreProperties("careers")
    private List<Teacher>teachers=new ArrayList<>();
}
