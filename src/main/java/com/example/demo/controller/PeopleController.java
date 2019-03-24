package com.example.demo.controller;

import com.example.demo.primaryEntity.Student;
import com.example.demo.primaryRepository.StudentRepo;
import com.example.demo.secondaryEntity.Teacher;
import com.example.demo.secondaryRepository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("people")
@RestController
@Component
public class PeopleController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @GetMapping("queryStu")
    public List<Student> queryStudent() {
        return studentRepo.findAll();
    }


    @GetMapping("saveStu")
    public void saveStu(@RequestParam("name") String name,
                            @RequestParam("id") Long id) {
        studentRepo.save(new Student(id, name));
    }

    @PostMapping("savePeople")
    public void savePeople(@RequestBody Teacher teacher) {
        System.out.println(teacher.getName());
        if (teacher != null || !teacherRepo.existsByName(teacher.getName())) {
            teacherRepo.save(teacher);
        }
    }

    @GetMapping("queryTeaAll")
    public List<Teacher> queryTeacher() {
        return teacherRepo.findAll();
    }

    @Transactional
    @Scheduled(cron = "0 0/10 * * * ?")
    public void timer() {
        Integer num = studentRepo.countAll();
        System.out.println("学生总人数为" + num);
        System.out.println("定时任务开启了=====》》》" + System.currentTimeMillis());
    }
}
