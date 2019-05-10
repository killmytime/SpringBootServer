package com.adweb.adwebserver.service;

import com.adweb.adwebserver.service.impl.TeacherServiceImpl;

public class ServiceFactory {
    public static TeacherService getTeacherServiceInstance(){
        return new TeacherServiceImpl();
    }
}
