package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/test")
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello() throws IOException {
        String hello=System.getProperty("user.dir");
        System.out.println("hello\n"+hello);
        return hello;
    }
    @PostMapping(path = "/contentTest")
    public @ResponseBody
    Post newPost(@Valid Post post){
        System.out.println(post.toString());
        return post;
    }
}
