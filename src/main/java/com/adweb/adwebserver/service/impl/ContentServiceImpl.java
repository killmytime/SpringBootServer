package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.Content;
import com.adweb.adwebserver.domain.repository.ContentRepository;
import com.adweb.adwebserver.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Override
    public Content addContent(Content content) {
        if (this.getContentByContentID(content.getContentId())==null)
        return contentRepository.save(content);
        return null;
    }

    @Override
    public Content getContentByContentID(String contentID) {
        return contentRepository.getContentByContentId(contentID);
    }

    @Override
    public Content modifyContent(Content content) {
        Content content1=this.getContentByContentID(content.getContentId());
        if (content1!=null){
            content1.setDialog(content.getDialog());
            content1.setQuestion(content.getQuestion());
            content1.setContentName(content.getContentName());
        return contentRepository.save(content1);
        }
        return null;
    }
}
