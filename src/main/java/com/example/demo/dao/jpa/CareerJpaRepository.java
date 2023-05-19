package com.example.demo.dao.jpa;

import com.example.demo.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerJpaRepository extends JpaRepository<Career,Long> {
}
