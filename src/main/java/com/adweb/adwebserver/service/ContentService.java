package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Content;

public interface ContentService {
    public Content addContent(Content content);//初次创建，在点击create之后
    public Content getContentByContentID(String contentID);
    public Content modifyContent(Content content);//修改内容之后，再次提交
}
