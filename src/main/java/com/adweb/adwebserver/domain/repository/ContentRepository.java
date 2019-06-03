package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Content;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content,Long> {
    public Content getContentByContentId(String contentID);
    public boolean existsContentByContentId(String contentId);
}
