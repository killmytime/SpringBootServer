package com.adweb.adwebserver.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DirectoryNode {
    private int DirectoryId;
    private int index;
    private String name;
}
