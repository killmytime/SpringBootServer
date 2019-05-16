package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Dialog {
    private String dialogId;
    private int kind;
    private String content;
}
