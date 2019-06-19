package com.adweb.adwebserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/test")
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello() throws IOException {
        String hello=System.getProperty("user.dir");
        File file=new File("image"+ UUID.randomUUID()+".jpg");
        if (!file.exists()){
            hello="file not created";
            file.createNewFile();
            hello+="*****"+file.getAbsolutePath();
        }
        System.out.println("hello\n"+hello);
        return hello;
    }
}
