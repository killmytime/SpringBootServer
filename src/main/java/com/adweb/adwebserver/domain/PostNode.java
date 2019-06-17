package com.adweb.adwebserver.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostNode {
    private String name;
    private String avatar;
    private String content;
}
