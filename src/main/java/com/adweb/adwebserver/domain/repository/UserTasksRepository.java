package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserTasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTasksRepository extends CrudRepository<UserTasks,Long> {
    List<UserTasks> getUserTasksByStudentId(int studentID);
    UserTasks getUserTasksByStudentIdAndContentId(int studentID, String contentID);
    @Query(value = "select taskId,studentId,contentId,contentName,question,answer,flag from UserTasks where contentId like %?1")//Todo 这里的语法可能有问题，定位先看Todo List
    List<UserTasks> getUserTasksByCourseId(int courseID);
    UserTasks getUserTasksByTaskId(int taskID);
}
