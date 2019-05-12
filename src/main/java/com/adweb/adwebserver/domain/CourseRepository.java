package com.adweb.adwebserver.domain;

import org.springframework.data.repository.CrudRepository;

        import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {
    public List<Course> getCoursesByTeacherId(int teacherId);
}
