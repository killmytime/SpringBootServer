package com.adweb.adwebserver.configuration;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfiguration {
    //修改@ResponseBody返回对象转成json的默认配置，使输出更好看
    @Bean
    public ObjectMapper getObjectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy - MM - dd HH：mm：ss"));
        return mapper;
    }
}

