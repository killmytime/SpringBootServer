package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.service.FileService;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements FileService {
    public static final String ROOT="upload-resources/";
    public static final String COURSE="course/";
    @Override
    public boolean uploadFile(String path, File file) {
        return false;
    }

    @Override
    public File showFile(String path) {
        return null;
    }
}
