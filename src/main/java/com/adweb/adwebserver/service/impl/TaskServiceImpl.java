package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Content;
import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.UserTasks;
import com.adweb.adwebserver.domain.repository.ContentRepository;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.domain.repository.UserTasksRepository;
import com.adweb.adwebserver.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    UserTasksRepository userTasksRepository;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Override
    public List<UserTasks> getUserTasksByStudentID(int studentID) {
        List<UserTasks> userTasks=userTasksRepository.getUserTasksByStudentId(studentID);
        userTasks.sort(Comparator.comparing(UserTasks::getContentId));
        return userTasks;
    }

    @Override
    public UserTasks generateUserTasks(int studentID, String contentID) {
        UserTasks userTask=new UserTasks();
        userTask.setStudentId(studentID);
        userTask.setContentId(contentID);
        if (userTasksRepository.getUserTasksByStudentIdAndContentId(studentID, contentID)!=null)
            return null;
        Content content=contentRepository.getContentByContentId(contentID);
        userTask.setContentName(content.getContentName());
        userTask.setQuestion(content.getQuestion());
        return userTasksRepository.save(userTask);
    }

    @Override
    public boolean setAnswer(int studentID, String contentID, String answer) {
        UserTasks userTask=userTasksRepository.getUserTasksByStudentIdAndContentId(studentID,contentID);
        userTask.setAnswer(answer);
        userTask.setFlag(1);//待审阅状态
        userTasksRepository.save(userTask);
        return true;
    }

    @Override
    public List<UserTasks> getStudentsTasksByCourseID(int courseID,int teacherID) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        if (course==null) return null;
        if (course.getTeacherId()!=teacherID) return null;
        return userTasksRepository.getUserTasksByCourseId(courseID);
    }

    @Override
    public int reviewUserTasksStatus(int taskID) {
        UserTasks userTask=userTasksRepository.getUserTasksByTaskId(taskID);
        userTask.setFlag(2);
        userTasksRepository.save(userTask);
        return 2;
    }

    @Override
    public UserTasks getUserTasksByTaskID(int taskID) {
        return userTasksRepository.getUserTasksByTaskId(taskID);
    }
}
