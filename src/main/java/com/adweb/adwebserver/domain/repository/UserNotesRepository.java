package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserNotes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserNotesRepository extends CrudRepository<UserNotes,Long> {
    UserNotes getUserNotesByNoteId(int noteID);
    List<UserNotes> getUserNotesByStudentId(int studentID);
}
