package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {

    public List<Course> getCoursesByTeacherId(int teacherId);
    public  Course getCourseByCourseId(int courseID);
    public Course removeCourseByCourseIdAndFlag(int courseID,int flag);
    public List<Course> getCoursesByFlag(int flag);
}
