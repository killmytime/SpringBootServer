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

import javax.persistence.Column;
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
        Course course=courseRepository.getCourseByCourseId(courseID);
        JSONArray directory=course.getDirectory();
        JSONArray processList=new JSONArray();
        JSONArray presentList=new JSONArray();
        for (int i=0;i<directory.size();i++){
            String directoryId=directory.getJSONObject(i).getString("directoryId");
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("contentId",directoryId);
            jsonObject1.put("isFinished",false);
            processList.add(jsonObject1);
            JSONObject jsonObject2=new JSONObject();
            jsonObject2.put("contentId",directoryId);
            jsonObject2.put("dialogId",0);
            presentList.add(jsonObject2);
        }
        UserProcess userProcess=new UserProcess();
        userProcess.setStudentId(studentID);
        userProcess.setCourseId(courseID);
        userProcess.setPresentList(presentList);
        userProcess.setProcessList(processList);
        return null!=userProcessRepository.save(userProcess);
    }

    @Override
    public boolean modifyProcess(int studentID, int courseID, PresentNode presentNode, ProcessNode processNode) {
        UserProcess userProcess=userProcessRepository.getUserProcessesByStudentIdAndCourseId(studentID,courseID);
        //Todo 在修改json列表之后再把数据正确存储
        JSONArray processList=userProcess.getProcessList();
        JSONArray presentList=userProcess.getPresentList();
        processList.add(JSONObject.toJSON(processList));
        presentList.add(JSONObject.toJSON(presentList));
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
