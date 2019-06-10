package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.UserTasks;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface TaskService {
    public List<UserTasks> getUserTasksByStudentID(int studentID);//学生根据学生id获取自己的任务
    public List<UserTasks> getUserTasksByStudentIDAndCourseID(int studentID,int courseID);//学生根据学生id和课程id获取自己的任务,根据contentID排序
    public UserTasks generateUserTasks(int studentID,String contentID);//在第一次打开某课程学习页面的时候生成对应的任务，其实就是最后的问题
    public boolean setAnswer(int studentID, String contentID, JSONObject answer);//设置完成状态，设置自己完成的答案
    public List<UserTasks> getStudentsTasksByCourseID(int courseID);//根据内容id获取学生任务，优先按照contentID排序
    public int modifyUserTasksStatus(int studentID,String contentID);//修改用户任务状态
    public int reviewUserTasksStatus(int taskID);//老师修改状态，状态分为未完成，待批阅，已审阅三个状态，Todo 评论内容要不要看情况吧
}
