package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.UserNotes;

import java.util.List;

public interface NoteService {
    UserNotes addNote(UserNotes userNotes);//在课程内容界面作笔记，所以只要关联到课程的列表
    UserNotes modifyNote(UserNotes userNotes);//修改笔记，根据studentID和contentID定位
    List<UserNotes> getNotesByStudentID(int studentID);//根据学生查询所有笔记

}
