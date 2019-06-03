package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Course;
import com.alibaba.fastjson.JSONArray;

public interface DirectoryService {
    public Course addNewDirectory(int courseID, JSONArray list);//这里创建新目录，修改课程id对应的目录id，暂不考虑彻底删除
    public Course  modifyDirectory(int courseID,JSONArray list);//说是修改，但是由于目录的特殊性，每次在本地生成好json提交~
    public Course deleteDirectory(int courseID);//删除Course里的目录内容
}
