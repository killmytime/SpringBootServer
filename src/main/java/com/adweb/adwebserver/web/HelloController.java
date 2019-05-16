package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Directory;
import com.adweb.adwebserver.domain.DirectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    DirectoryRepository directoryRepository;
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @GetMapping("/test")
    public @ResponseBody
    Directory test(){
        return directoryRepository.getDirectoryByDirectoryId(1);
    }
}
