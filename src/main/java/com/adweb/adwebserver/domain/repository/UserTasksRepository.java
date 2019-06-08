package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserTasks;
import org.springframework.data.repository.CrudRepository;

public interface UserTasksRepository extends CrudRepository<UserTasks,Long> {
}
