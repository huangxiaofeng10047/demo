package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author hxf
 * @date 2023年05月18日 17:00
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "score")
public class Score {
    @Id
    @Column(name = "score_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer score;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
