package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PostNode {
    int studentID;
    String content;
    public PostNode(int studentID, String content) {
        this.studentID = studentID;
        this.content = content;
    }
}
