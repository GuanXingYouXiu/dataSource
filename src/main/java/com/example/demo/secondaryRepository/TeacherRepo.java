package com.example.demo.secondaryRepository;

import com.example.demo.secondaryEntity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    boolean existsByName(String name);
}
