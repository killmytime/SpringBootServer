package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//ToDo 可能要改一下
//[directoryId:"1"]
public class DirectoryNode {
    private String directoryId;//子目录，同时也是contentID 1_1,1_2的形式
    private String name;//目录名字
}

