package com.example.demo.controller;

import com.example.demo.Service.ThemeInfoService;
import com.example.demo.primaryEntity.Student;
import com.example.demo.primaryEntity.ThemeInfo;
import com.example.demo.primaryRepository.StudentRepo;
import com.example.demo.primaryRepository.ThemeInfoRepo;
import com.example.demo.secondaryEntity.Teacher;
import com.example.demo.secondaryRepository.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequestMapping("people")
@RestController
@Component
public class PeopleController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeacherRepo teacherRepo;

    @Autowired
    private ThemeInfoRepo themeInfoRepo;

    @Autowired
    private ThemeInfoService themeInfoService;


    @GetMapping("queryStuAll")
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


    /*同步数据*/
    @PostMapping("synchronize")
    public void synchronizeThemeInfo(@RequestBody ThemeInfo themeInfo) {
        themeInfo.setPublishTime(LocalDateTime.now());
        Optional<ThemeInfo> themeInfoOptional =themeInfoRepo.findById(themeInfo.getId());
        if(!themeInfoOptional.isPresent()){
            themeInfoRepo.save(themeInfo);
            themeInfoService.synchronizeThemeToXuanke(themeInfo);
        }
    }

    /*同步删除数据*/
    @PostMapping("delete")
    public void deleteThemeInfo(@RequestBody List<Long> Ids) {
        for (Long id : Ids) {
            themeInfoRepo.delete(themeInfoRepo.findById(id).get());
        }
        themeInfoService.synchronizeDeleteThemeToXuanke(Ids);
    }

    /*同步修改数据*/
    @PostMapping("update")
    public void updateThemeInfo(@RequestBody List<Long> Ids) {
        List<ThemeInfo> themeInfos = themeInfoRepo.findAllById(Ids);
        for (ThemeInfo themeInfo : themeInfos) {
            if (themeInfo != null) {
                themeInfo.setIsHot(1);
                themeInfoRepo.save(themeInfo);
            }
        }
        themeInfoService.synchronizeUpdateThemeToXuanke(Ids);
    }
}
