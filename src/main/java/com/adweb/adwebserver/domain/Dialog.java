package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Dialog {
    private int dialogId;//考虑从0开始自增吧，正好契合前端的数组，减少工作量
    private int kind;//0，1，2，3，4
    private String content;//内容或者url
}
