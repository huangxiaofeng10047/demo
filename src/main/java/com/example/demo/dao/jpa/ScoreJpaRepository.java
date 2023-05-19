package com.example.demo.dao.jpa;

import com.example.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreJpaRepository extends JpaRepository<Score,Long> {
}
