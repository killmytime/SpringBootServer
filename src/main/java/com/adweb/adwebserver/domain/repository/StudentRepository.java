package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StudentRepository extends CrudRepository<Student,Long> {
    //@Query("select u from Student u where u.name=?1")
    //Collection<Student> findStudentsByName(String name);
    Student getStudentByWechatId(String id);
    Student getStudentByStudentId(int id);
}
