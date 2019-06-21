package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.service.DirectoryService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {
    @Autowired
    CourseRepository courseRepository;
    @Override
    public Course addNewDirectory(int courseID, JSONObject directory) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        if (null==course) return null;
        JSONArray tmp=course.getDirectory();
        if (tmp==null) tmp=new JSONArray();
        tmp.add(directory);
        course.setDirectory(tmp);
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
