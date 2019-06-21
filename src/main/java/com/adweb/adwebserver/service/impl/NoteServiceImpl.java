package com.adweb.adwebserver.service.impl;

import com.adweb.adwebserver.domain.UserNotes;
import com.adweb.adwebserver.domain.repository.UserNotesRepository;
import com.adweb.adwebserver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    UserNotesRepository userNotesRepository;
    @Override
    public UserNotes addNote(UserNotes userNotes) {
        System.out.println("=====================");
        System.out.println(userNotes.getStudentId());
        System.out.println(userNotes.getContentId());
        System.out.println(userNotes.getContentName());
        System.out.println(userNotes.getNote());
        System.out.println("=====================");
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
