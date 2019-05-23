package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Student;
import com.adweb.adwebserver.domain.repository.StudentRepository;
import com.adweb.adwebserver.service.CourseService;
import com.adweb.adwebserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public @ResponseBody
    Student update(@Valid Student student){
        return studentService.update(student);
    }

    //todo 正在完善 根据学生的id获取该学生选的所有课
    @GetMapping(path = "/allCourse")
    public @ResponseBody
    List<Course> allCourse(@RequestParam int studentID) {
        return studentService.getAllCourse(studentID);
    }

    //todo 正在完善 根据具体课程的id获取具体的一门课 是否保留studentID以便获取用户学习情况等等？
    @GetMapping(path = "/detailCourse")
    public @ResponseBody Course detailCourse(@RequestParam int studentID, @RequestParam int courseID) {
        return studentService.getDetailCourse(studentID, courseID);
    }

    //todo 正在完善 更新学生学习课程的信息 比如学习进度等
    @PostMapping(path = "/updateCourse")
    public @ResponseBody Course updateCourse(@RequestParam int studentID, @RequestParam int courseID) {
        //return studentService.update(@Valid Course course)
        return null;
    }

    @GetMapping(path = "/info")
    public @ResponseBody Student getInfo(@RequestParam int studentID){
        Student student = new Student();
        student.setStudentId(studentID);
        return studentService.getStudent(student);
    }



}
