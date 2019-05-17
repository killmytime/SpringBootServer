package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
//ToDo 可能要改一下
//[directoryId:"1"]
public class DirectoryNode {
    private int DirectoryId;
    private boolean isSecond;
    private String name;
}

