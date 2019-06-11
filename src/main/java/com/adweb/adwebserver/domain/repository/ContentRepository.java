package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Content;
import org.springframework.data.repository.CrudRepository;

public interface ContentRepository extends CrudRepository<Content,Long> {
    Content getContentByContentId(String contentID);
    boolean existsContentByContentId(String contentId);
}
