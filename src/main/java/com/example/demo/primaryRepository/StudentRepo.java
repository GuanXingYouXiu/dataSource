package com.example.demo.primaryRepository;

import com.example.demo.primaryEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepo extends JpaRepository<Student,Long> {

    /* 统计学生人数*/
    @Query(nativeQuery = true, value = "select count(distinct v.id) from student v ")
    Integer countAll();
}
