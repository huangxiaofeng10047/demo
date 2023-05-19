package com.example.demo.dao.jpa;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectJpaRepository extends JpaRepository<Subject,Long> {
}
