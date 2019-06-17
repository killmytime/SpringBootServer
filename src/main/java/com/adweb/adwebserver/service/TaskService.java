package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.UserTasks;

import java.util.List;

public interface TaskService {
    List<UserTasks> getUserTasksByStudentID(int studentID);//学生根据学生id获取自己的任务，排序问题可以按照contentID排序，好像不排也没有关系，毕竟有时候还得按照课程排名
    UserTasks generateUserTasks(int studentID, String contentID);//在第一次打开某课程学习页面的时候生成对应的任务，其实就是最后的问题
    boolean setAnswer(int studentID, String contentID, String answer);//设置完成状态，设置自己完成的答案,考虑到只是展示，问题也不会太复杂，答案就限制为String类型
    List<UserTasks> getStudentsTasksByCourseID(int courseID,int teacherID);//根据内容id获取学生任务，优先按照contentID排序
    int reviewUserTasksStatus(int taskID);//老师修改状态，状态分为未完成0，待批阅1，已审阅2三个状态，此处仅查看待批阅状态的任务 Todo 评论内容要不要看情况吧
    UserTasks getUserTasksByTaskID(int taskID);
}
