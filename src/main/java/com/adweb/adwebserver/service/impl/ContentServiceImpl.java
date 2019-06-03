package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Content;
import com.adweb.adwebserver.domain.repository.ContentRepository;
import com.adweb.adwebserver.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Override
    public Content addContent(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public Content getContentByContentID(String contentID) {
        return contentRepository.getContentByContentId(contentID);
    }

    @Override
    public Content modifyContent(Content content) {
        return contentRepository.save(content);
    }
}
