package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.UserTasks;

import java.util.List;

public interface TaskService {
    public List<UserTasks> getUserTasksByStudentID(int studentID);
}
