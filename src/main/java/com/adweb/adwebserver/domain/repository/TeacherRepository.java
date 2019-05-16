package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
    Teacher findTeacherByNumberAndPassword(int number,String password);
    Teacher getTeacherByTeacherId(int teacherId);
}
