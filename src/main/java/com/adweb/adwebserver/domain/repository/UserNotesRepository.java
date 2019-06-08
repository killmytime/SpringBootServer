package com.adweb.adwebserver.domain.repository;

import com.adweb.adwebserver.domain.UserNotes;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserNotesRepository extends CrudRepository<UserNotes,Long> {
    public UserNotes getUserNotesByNoteId(int noteID);
    public List<UserNotes> getUserNotesByStudentId(int studentID);
}
