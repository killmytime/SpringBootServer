package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.PresentNode;
import com.adweb.adwebserver.domain.ProcessNode;
import com.adweb.adwebserver.domain.UserProcess;
import com.adweb.adwebserver.domain.repository.UserProcessRepository;
import com.adweb.adwebserver.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessServiceImpl implements ProcessService {
    @Autowired
    UserProcessRepository userProcessRepository;
    @Override
    public boolean initProcess(int studentID, int courseID) {
        UserProcess userProcess=new UserProcess();
        userProcess.setStudentId(studentID);
        userProcess.setCourseId(courseID);
        return null!=userProcessRepository.save(userProcess);
    }

    @Override
    public boolean modifyProcess(int studentID, int courseID, PresentNode presentNode, ProcessNode processNode) {
        return false;
    }

    @Override
    public UserProcess getUserProcess(int studentID, int courseID) {
        return null;
    }
}
