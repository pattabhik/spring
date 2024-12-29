package com.pattabhi.rest_api.thymeleaf.controller;

import com.pattabhi.rest_api.student.entity.StudentEntity;
import com.pattabhi.rest_api.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HelloController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello2")
    public ModelAndView hello2() {
        ModelAndView hello = new ModelAndView("hello2");
        hello.addObject("message", "Hello World");
        return hello;
    }

    @GetMapping("/student")
    public ModelAndView getStudent() {
        ModelAndView student = new ModelAndView("student");
        StudentEntity pattabhi = new StudentEntity();
        pattabhi.setFirstName("Pattabhi");
        pattabhi.setLastName("Kumar");
        student.addObject("student", pattabhi);
        return student;
    }

    @GetMapping("/mvc-students")
    public ModelAndView getStudents() {
        ModelAndView students = new ModelAndView("students");
        students.addObject("students", studentRepository.findAll());
        return students;
    }

    @GetMapping("/student-view")
    public ModelAndView displayStudent() {
        ModelAndView studentView = new ModelAndView("studentForm");
        Optional<StudentEntity> student = studentRepository.findById(5);
        studentView.addObject("student", student);
        return studentView;
    }

    @PostMapping("/save-student")
    public ModelAndView saveStudent(@ModelAttribute StudentEntity student) {
        ModelAndView studentView = new ModelAndView("studentForm");
        studentRepository.save(student);

        Optional<StudentEntity> refreshedStudent = studentRepository.findById(student.getId());
        studentView.addObject("student", refreshedStudent);
        return studentView;
    }
}
