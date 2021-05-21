package com.ninos.controller;

import com.ninos.model.Student;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class TestingController {


    private List<Student> students = new ArrayList<>();

   /* public TestingController() {
        students.add(new Student(1L,"ninos","40"));
        students.add(new Student(2L,"nahrain","34"));
        students.add(new Student(3L,"matthew","8"));
        students.add(new Student(4L,"daniel","6"));
    }*/

    @PostConstruct
    public void init(){
        students.add(new Student(1L,"ninos","40"));
        students.add(new Student(2L,"nahrain","34"));
        students.add(new Student(3L,"matthew","8"));
        students.add(new Student(4L,"daniel","6"));
    }


    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return students;
    }

    
}
