package com.tutorial.springsecurity.controller;

import com.tutorial.springsecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {
    List<Student> allStudents =new ArrayList<>(List.of(
            new Student("1", "Aritra", "100", "1"),
            new Student("2", "Rup", "90", "2")


    ));

    @GetMapping("/security")
    public String testController(HttpServletRequest req) {
        return "This is a demo page " + "with Session Id " + req.getSession().getId();
    }

    @GetMapping("/students")
    public List<Student> getAllStudenst() {

        return allStudents;

    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student s)
    {
        this.allStudents.add(s);
        return  s;
    }


}
