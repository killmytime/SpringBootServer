package com.adweb.adwebserver.domain;

import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
    public Teacher findTeacherByNumberAndPassword(int number,String password);
}
