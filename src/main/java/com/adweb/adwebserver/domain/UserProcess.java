package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Component
@Entity
@Table(name = "user_process", schema = "web")
public class UserProcess {
    private int processId;
    private int studentId;
    private int courseId;
    private JSONArray processList;
    private JSONArray presentList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processID")
    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
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
    @Column(name = "courseID")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "process_list")
    public JSONArray getProcessList() {
        return processList;
    }

    public void setProcessList(JSONArray processList) {
        this.processList = processList;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "present_list")
    public JSONArray getPresentList() {
        return presentList;
    }

    public void setPresentList(JSONArray presentList) {
        this.presentList = presentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProcess that = (UserProcess) o;
        return processId == that.processId &&
                studentId == that.studentId &&
                courseId == that.courseId &&
                Objects.equals(processList, that.processList) &&
                Objects.equals(presentList, that.presentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(processId, studentId, courseId, processList, presentList);
    }
}
