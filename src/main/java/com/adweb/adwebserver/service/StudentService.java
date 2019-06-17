package com.adweb.adwebserver.service;


import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    Student login(Student student);
    Student update(Student student);
    Student getStudent(Student student);
    Student getStudentByWechatID(String wechatID);
    //List<Course> getAllCourse(int studentID);CourseService中已经有了
    //Course getDetailCourse(int studentID, int courseID);CourseService中已经有了
    /*


    public List<Course> getAllCourseByTeacherID(int teacherID);
    public Course updateCourse(Teacher teacher,Course course);
    public boolean updateDirectory(JSONPObject directory,Course course);//更新目录包括添加目录，添加乱七八糟的等 Todo 问题在于改变顺序，重命名啥的，需要对唯一id进行操作吗，或者可以考虑加一个上下文索引·
    public void updateContent();//待定*/
}
