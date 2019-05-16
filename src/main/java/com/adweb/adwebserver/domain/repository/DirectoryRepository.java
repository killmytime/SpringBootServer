package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.Directory;
import org.springframework.data.repository.CrudRepository;

public interface DirectoryRepository extends CrudRepository<Directory,Long> {
    public Directory getDirectoryByDirectoryId(int id);
}
