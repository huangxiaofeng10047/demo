package com.example.demo.dao.jpa;

import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherJpaRepository extends JpaRepository<Teacher,Long> {
}
