package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONObject;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@TypeDef(name = "json", typeClass = JsonStringType.class)
@Component
@Entity
@Table(name = "user_tasks", schema = "web")
public class UserTasks {
    private int taskId;
    private int studentId;
    private int contentId;
    private String contentName;
    private JSONObject question;
    private JSONObject answer;

    @Id
    @Column(name = "taskID")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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
    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
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
    @Type(type = "json")
    @Column(name = "question")
    public JSONObject getQuestion() {
        return question;
    }

    public void setQuestion(JSONObject question) {
        this.question = question;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "answer")
    public JSONObject getAnswer() {
        return answer;
    }

    public void setAnswer(JSONObject answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTasks userTasks = (UserTasks) o;
        return taskId == userTasks.taskId &&
                studentId == userTasks.studentId &&
                contentId == userTasks.contentId &&
                Objects.equals(contentName, userTasks.contentName) &&
                Objects.equals(question, userTasks.question) &&
                Objects.equals(answer, userTasks.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, studentId, contentId, contentName, question, answer);
    }
}
