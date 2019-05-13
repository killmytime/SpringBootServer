package com.adweb.adwebserver.web;

import java.util.concurrent.atomic.AtomicLong;

import com.adweb.adwebserver.domain.Student;
import com.adweb.adwebserver.domain.StudentRepository;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/students")
public class StudentsController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Student> getStudents(){
        //This returns a JSON or XML with the students
        return studentRepository.findAll();
    }
    @GetMapping(path = "/login")
    public @ResponseBody Student login(@RequestParam String wechatId) {
        return studentService.login(wechatId);
    }

    @PostMapping(path = "/update")
    public @ResponseBody Student update(@Valid Student student){
        return studentService.update(student);
    }

    @GetMapping(path = "/info")
    public @ResponseBody Student getInfo(@RequestParam int studentID){
        Student student = new Student();
        student.setStudentId(studentID);
        return studentService.getStudent(student);
    }



}
