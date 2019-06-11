package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {

    List<Course> getCoursesByTeacherId(int teacherId);
    Course getCourseByCourseId(int courseID);
    Course removeCourseByCourseIdAndFlag(int courseID, int flag);
    List<Course> getCoursesByFlag(int flag);
}
