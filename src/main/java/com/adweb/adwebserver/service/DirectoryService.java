package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Course;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public interface DirectoryService {
    Course addNewDirectory(int courseID, JSONObject directory);//这里创建新目录，最终决定每次添加新的目录然后并查看
    Course  modifyDirectory(int courseID, JSONArray list);//说是修改，但是由于目录的特殊性，每次在本地生成好json提交~
    Course deleteDirectory(int courseID);//删除Course里的目录内容
}
