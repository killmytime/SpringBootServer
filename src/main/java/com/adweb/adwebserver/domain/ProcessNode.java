package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProcessNode {
    String courseID;
    boolean isFinished;
}