package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserProcessRepository extends CrudRepository<UserProcess,Long> {
    List<UserProcess> getUserProcessesByStudentId(int studentID);
    UserProcess getUserProcessesByStudentIdAndCourseId(int studentID, int courseID);
    List<UserProcess> getUserProcessesByCourseId(int courseID);
}
