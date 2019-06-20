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
    private String contentId;
    private String contentName;
    private JSONObject question;
    private String answer;
    private int flag;//0未完成，1待审阅，2已审阅

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Type(type = "json")
    @Column(name = "question")
    public JSONObject getQuestion() {
        return question;
    }

    public void setQuestion(JSONObject question) {
        this.question = question;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "flag")
    public int getFlag(){return flag;}

    public void setFlag(int flag){this.flag=flag;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTasks userTasks = (UserTasks) o;
        return taskId == userTasks.taskId &&
                studentId == userTasks.studentId &&
                contentId.equals(userTasks.contentId) &&
                Objects.equals(contentName, userTasks.contentName) &&
                Objects.equals(question, userTasks.question) &&
                Objects.equals(answer, userTasks.answer)&&
                flag==userTasks.flag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, studentId, contentId, contentName, question, answer,flag);
    }
}
