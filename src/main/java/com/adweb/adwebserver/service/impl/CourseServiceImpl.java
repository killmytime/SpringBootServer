package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Course addNewCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course modifyCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourseByFlag(int flag) {
        return courseRepository.getCoursesByFlag(flag);
    }

    @Override
    public Course getCourseByID(int courseID) {
        return courseRepository.getCourseByCourseId(courseID);
    }

    @Override
    public Course postCourse(Course course) {
        Course course1=courseRepository.getCourseByCourseId(course.getCourseId());
        if (0==course1.getFlag())
        return courseRepository.save(course1);
        else return null;
    }

    @Override
    public Course deleteCourse(Course course) {
        return courseRepository.removeCourseByCourseIdAndFlag(course.getCourseId(),0);
    }
}
