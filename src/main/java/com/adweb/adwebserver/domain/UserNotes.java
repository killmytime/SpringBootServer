package com.adweb.adwebserver.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Component
@Entity
@Table(name = "user_notes", schema = "web")
public class UserNotes {
    private int noteId;
    private int studentId;
    private String contentId;
    private String contentName;
    private String note;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noteID")
    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    @Basic
    @Column(name = "studentID")
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "contentID")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "content_name")
    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    @Basic
    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserNotes userNotes = (UserNotes) o;
        return noteId == userNotes.noteId &&
                studentId == userNotes.studentId &&
                contentId.equals(userNotes.contentId) &&
                Objects.equals(contentName, userNotes.contentName) &&
                Objects.equals(note, userNotes.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, studentId, contentId, contentName, note);
    }
}
