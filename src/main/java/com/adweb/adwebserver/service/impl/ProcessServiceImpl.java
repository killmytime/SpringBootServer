package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.PresentNode;
import com.adweb.adwebserver.domain.ProcessNode;
import com.adweb.adwebserver.domain.UserProcess;
import com.adweb.adwebserver.domain.repository.CourseRepository;
import com.adweb.adwebserver.domain.repository.UserProcessRepository;
import com.adweb.adwebserver.service.ProcessService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    UserProcessRepository userProcessRepository;
    @Autowired
    CourseRepository courseRepository;
    @Override
    public boolean initProcess(int studentID, int courseID) {
        if (null!=userProcessRepository.getUserProcessesByStudentIdAndCourseId(studentID,courseID)) return true;
        UserProcess userProcess=new UserProcess();
        userProcess.setStudentId(studentID);
        userProcess.setCourseId(courseID);
        return null!=userProcessRepository.save(userProcess);
    }

    @Override
    public boolean modifyProcess(int studentID, int courseID, PresentNode presentNode, ProcessNode processNode) {
        UserProcess userProcess=userProcessRepository.getUserProcessesByStudentIdAndCourseId(studentID,courseID);
        //Todo 在修改json列表之后再把数据正确存储
        JSONArray processList=userProcess.getProcessList();
        JSONArray presentList=userProcess.getPresentList();
        processList.add(JSONObject.toJSON(presentNode));
        presentList.add(JSONObject.toJSON(presentNode));
        userProcess.setProcessList(processList);
        userProcess.setPresentList(presentList);
        userProcessRepository.save(userProcess);
        return true;
    }

    @Override
    public UserProcess getUserProcess(int studentID, int courseID) {
        return userProcessRepository.getUserProcessesByStudentIdAndCourseId(studentID,courseID);
    }

    @Override
    public List<UserProcess> getUserProcessesByCourseID(int courseID,int teacherID) {
        Course course=courseRepository.getCourseByCourseId(courseID);
        if (course==null) return null;
        if (course.getTeacherId()!=teacherID) return null;
        return userProcessRepository.getUserProcessesByCourseId(courseID);
    }
}
