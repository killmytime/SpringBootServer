package com.adweb.adwebserver.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/test")
public class HelloController {
    @GetMapping(path = "/hello")
    public String hello(){
        String hello="hello";
        System.out.println("hello");
        return hello;
    }
}
