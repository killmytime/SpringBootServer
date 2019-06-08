package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.UserNotes;
import com.adweb.adwebserver.domain.repository.UserNotesRepository;
import com.adweb.adwebserver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Autowired
    UserNotesRepository userNotesRepository;
    @Override
    public UserNotes addNote(UserNotes userNotes) {
        return userNotesRepository.save(userNotes);
    }

    @Override
    public UserNotes modifyNote(UserNotes userNotes) {
        UserNotes userNotes1=userNotesRepository.getUserNotesByNoteId(userNotes.getNoteId());
        userNotes1.setNote(userNotes.getNote());
        return userNotesRepository.save(userNotes1);
    }

    @Override
    public List<UserNotes> getNotesByStudentID(int studentID) {
        return userNotesRepository.getUserNotesByStudentId(studentID);
    }
}
