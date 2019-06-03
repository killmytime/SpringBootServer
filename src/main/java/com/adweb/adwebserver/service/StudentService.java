package com.adweb.adwebserver.service;


import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    public Student login(String wechatId);
    public Student update(Student student);
    public Student getStudent(Student student);
    public List<Course> getAllCourse(int studentID);
    public Course getDetailCourse(int studentID, int courseID);
    /*


    public List<Course> getAllCourseByTeacherID(int teacherID);
    public Course updateCourse(Teacher teacher,Course course);
    public boolean updateDirectory(JSONPObject directory,Course course);//更新目录包括添加目录，添加乱七八糟的等 Todo 问题在于改变顺序，重命名啥的，需要对唯一id进行操作吗，或者可以考虑加一个上下文索引·
    public void updateContent();//待定*/
}
