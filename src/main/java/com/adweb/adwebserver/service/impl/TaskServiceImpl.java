package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.UserTasks;
import com.adweb.adwebserver.service.TaskService;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public List<UserTasks> getUserTasksByStudentID(int studentID) {
        return null;
    }

    @Override
    public List<UserTasks> getUserTasksByStudentIDAndCourseID(int studentID, int courseID) {
        return null;
    }

    @Override
    public UserTasks generateUserTasks(int studentID, String contentID) {
        return null;
    }

    @Override
    public boolean setAnswer(int studentID, String contentID, JSONObject answer) {
        return false;
    }

    @Override
    public List<UserTasks> getStudentsTasksByCourseID(int courseID) {
        return null;
    }

    @Override
    public int modifyUserTasksStatus(int studentID, String contentID) {
        return 0;
    }

    @Override
    public int reviewUserTasksStatus(int taskID) {
        return 0;
    }
}
