package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Teacher;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TeacherService {
    Teacher register(Teacher teacher);//老师注册，需要邀请码，机制在filter里实现
    Teacher login(String number, String password);//老师登陆，账号密码
    int getTeacherIdByNumberAndPassword(String number,String password);//根据手机号码和密码获得老师id
    Teacher update(Teacher teacher);//更新老师信息
    Teacher getTeacher(Teacher teacher);//敏感信息设置为空,根据teacherId查询Teacher
    boolean setPassword(Teacher teacher, String newPassword);//
    List<Course> getAllCourseByTeacherID(int teacherID);
    //Course updateCourse(Teacher teacher, Course course);CourseService中已有
    boolean updateDirectory(JSONPObject directory, Course course);//更新目录包括添加目录，添加乱七八糟的等 Todo 问题在于改变顺序，重命名啥的，需要对唯一id进行操作吗，或者可以考虑加一个上下文索引·
    void updateContent();//待定
}
