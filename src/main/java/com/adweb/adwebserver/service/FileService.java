package com.adweb.adwebserver.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
public interface FileService {
    String uploadFile(MultipartFile file) throws IOException;//返回上传文件的url路径
    boolean isFileExist(String filename);//检测当前文件是否为从服务器下载的
}
