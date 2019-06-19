package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Service
public class FileServiceImpl  implements FileService {
    String url="47.101.189.80:28080/img/";
    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        if (isFileExist(fileName)) return url+fileName;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        System.out.println(fileName);
        file.transferTo(new File("/"+fileName));
        return url+fileName;
    }

    @Override
    public boolean isFileExist(String filename) {
        File file=new File("/"+filename);
        return file.exists();
    }
}
