package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserProcess;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserProcessRepository extends CrudRepository<UserProcess,Long> {
    public List<UserProcess> getUserProcessesByStudentId(int studentID);
    public UserProcess getUserProcessesByStudentIdAndCourseId(int studentID,int courseID);
}
