package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Content;

public interface ContentService {
    Content addContent(Content content);//初次创建，在点击create之后
    Content getContentByContentID(String contentID);
    Content modifyContent(Content content);//修改内容之后，再次提交
}
