package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.TeacherRepository;
import com.adweb.adwebserver.service.TeacherService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher register(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher login(int number, String password) {
        return teacherRepository.findTeacherByNumberAndPassword(number,password);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher getTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public boolean setPassword(Teacher teacher, String newPassword) {
        return false;
    }

    @Override
    public List<Course> getAllCourseByTeacherID(int teacherID) {
        return null;
    }

    @Override
    public Course updateCourse(Teacher teacher, Course course) {
        return null;
    }

    @Override
    public boolean updateDirectory(JSONPObject directory, Course course) {
        return false;
    }

    @Override
    public void updateContent() {

    }
}
