package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
public class PresentNode {
    String courseID;
    int dialogID;
}
