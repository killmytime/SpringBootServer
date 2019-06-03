package com.adweb.adwebserver.domain;

import lombok.Data;

@Data
public class Question {
    //ToDO 问题need to 扩展
    private String question;//问题名字
    private String detail;//问题详情
    private String[] images;//问题的图片资源
    private String answer;//问题答案
}
