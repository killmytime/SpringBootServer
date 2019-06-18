package com.adweb.adwebserver.service;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface FileService {
    public boolean uploadFile(String path, File file);
    public File showFile(String path);
}
