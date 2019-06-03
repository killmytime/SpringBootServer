package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//ToDo 可能要改一下
//[directoryId:"1"]
public class DirectoryNode {
    private String directoryID;//如果是子目录，同时也是contentID
    private boolean isSecond;//是否还有下层结构，即是否是最下层1目录
    private String name;//目录名字
}

