package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.*;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.domain.repository.StudentRepository;
import com.adweb.adwebserver.service.CourseService;
import com.adweb.adwebserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关于身份校验的东西，之后再详细搞出一个解决方案
 * 从代码简洁性角度，这里的更新功能都是copy原版本，然后在原版本基础上修改，在更新回去~~效率上可能存在一点问题，不过小型pj问题不大~
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Student login(String wechatId) {
        /*Student st = studentRepository.getStudentByWechatId(wechatId);
        studentRepository.save(st);*/
        return studentRepository.getStudentByWechatId(wechatId);
    }

    @Override
    public Student update(Student student) {
        Student student0 = studentRepository.getStudentByStudentId(student.getStudentId());
        student0.setNumber(student.getNumber());
        student0.setEmail(student.getEmail());
        student0.setName(student.getName());
        student0.setAvatar(student.getAvatar());
        return studentRepository.save(student0);
    }

    @Override
    public Student getStudent(Student student) {
        int studentID = student.getStudentId();
        System.out.println(student.toString());
        student = studentRepository.getStudentByStudentId(studentID);

        return student;
    }

    //todo courseRepository里要实现下面两个方法
    //@Override
    public List<Course> getAllCourse(int studentID) {
        //return courseRepository.getCoursesByStudentId(studentID);
        return null;
    }

    //@Override
    public Course getDetailCourse(int studentID, int courseID) {
        //return courseRepository.getCoursesByStudentIDAndCourseID();
        return null;
    }


}
