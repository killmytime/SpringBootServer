package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.repository.TeacherRepository;
import com.adweb.adwebserver.service.TeacherService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 关于身份校验的东西，之后再详细搞出一个解决方案
 * 从代码简洁性角度，这里的更新功能都是copy原版本，然后在原版本基础上修改，在更新回去~~效率上可能存在一点问题，不过小型pj问题不大~
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Teacher register(Teacher teacher) {
        if (teacherRepository.getTeacherByNumber(teacher.getNumber())==null)
        return teacherRepository.save(teacher);
        return null;
    }

    @Override
    public Teacher login(String number, String password) {
        return teacherRepository.findTeacherByNumberAndPassword(number,password);
    }

    @Override
    public int getTeacherIdByNumberAndPassword(String number, String password) {
        Teacher teacher=teacherRepository.findTeacherByNumberAndPassword(number,password);
        return teacher!=null?teacher.getTeacherId():0;
    }

    @Override
    public Teacher update(Teacher teacher) {
        Teacher teacher0=teacherRepository.getTeacherByTeacherId(teacher.getTeacherId());
        //teacher0.setNumber(teacher.getNumber());
        teacher0.setEmail(teacher.getEmail());
        teacher0.setName(teacher.getName());
        teacher0.setAvatar(teacher.getAvatar());
        return teacherRepository.save(teacher0);
    }

    @Override
    public Teacher getTeacher(Teacher teacher) {
        int teacherID=teacher.getTeacherId();
        teacher =teacherRepository.getTeacherByTeacherId(teacherID);
        teacher.setPassword("");
        return teacher;
    }

    @Override
    public boolean setPassword(Teacher teacher, String newPassword) {
        Teacher teacher0=teacherRepository.findTeacherByNumberAndPassword(teacher.getNumber(),teacher.getPassword());
        if (teacher.getPassword().equals(teacher0.getPassword())) {
            teacher0.setPassword(newPassword);
            teacherRepository.save(teacher0);
            return true;
        }
        return false;
    }
//ToDO 以下部分待定
    @Override
    public List<Course> getAllCourseByTeacherID(int teacherID) {
        return courseRepository.getCoursesByTeacherId(teacherID);
    }

    /*@Override
    public Course updateCourse(Teacher teacher, Course course) {
        return null;
    }*/

    @Override
    public boolean updateDirectory(JSONPObject directory, Course course) {
        return false;
    }

    @Override
    public void updateContent() {
    }
}
