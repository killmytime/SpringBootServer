package com.adweb.adwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AdwebserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdwebserverApplication.class, args);
    }

}
