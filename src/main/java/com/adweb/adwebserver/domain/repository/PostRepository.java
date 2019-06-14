package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Post;
import com.adweb.adwebserver.domain.Student;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PostRepository extends CrudRepository<Post,Long> {
    //@Query("select u from Student u where u.name=?1")
    //Collection<Student> findStudentsByName(String name);
    Post getPostByPostId(int postID);
}
