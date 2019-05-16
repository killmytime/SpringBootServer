package com.adweb.adwebserver.domain;

import org.springframework.data.repository.CrudRepository;

public interface DirectoryRepository extends CrudRepository<Directory,Long> {
    public Directory getDirectoryByDirectoryId(int id);
}
