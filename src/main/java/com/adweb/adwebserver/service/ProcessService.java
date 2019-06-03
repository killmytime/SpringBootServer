package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.PresentNode;
import com.adweb.adwebserver.domain.ProcessNode;
import com.adweb.adwebserver.domain.UserProcess;

public interface ProcessService {
    public boolean initProcess(int studentID,int courseID);
    public boolean modifyProcess(int studentID, int courseID, PresentNode presentNode,ProcessNode processNode);
    public UserProcess getUserProcess(int studentID,int courseID);
}
