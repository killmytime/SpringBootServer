package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Dialog {
    private String dialogId;
    private int kind;//0，1，2，3，4
    private String content;//内容或者url
}
