package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.PresentNode;
import com.adweb.adwebserver.domain.ProcessNode;
import com.adweb.adwebserver.domain.UserProcess;

import java.util.List;

public interface ProcessService {
    boolean initProcess(int studentID, int courseID);
    boolean modifyProcess(int studentID, int courseID, PresentNode presentNode, ProcessNode processNode);
    UserProcess getUserProcess(int studentID, int courseID);
    List<UserProcess> getUserProcessesByCourseID(int courseID,int teacherID);
}
