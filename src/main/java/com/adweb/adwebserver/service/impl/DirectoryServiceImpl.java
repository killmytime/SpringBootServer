package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.service.DirectoryService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Course addNewDirectory(int courseID, JSONArray list) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        course.setDirectory(list);
        return courseRepository.save(course);
    }

    @Override
    public Course modifyDirectory(int courseID, JSONArray list) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        course.setDirectory(list);
        return courseRepository.save(course);
    }

    @Override
    public Course deleteDirectory(int courseID) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        course.setDirectory(new JSONArray());
        return courseRepository.save(course);
    }
}
