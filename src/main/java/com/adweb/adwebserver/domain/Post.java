package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Entity
public class Post {
    private int postId;
    private int studentId;
    private int courseId;
    private JSONArray text;
    private int clap;

    @Id
    @Column(name = "postID")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
    @Column(name = "test")
    public JSONArray getText() {
        return text;
    }

    public void setText(JSONArray text) {
        this.text = text;
    }

    @Basic
    @Column(name = "clap")
    public int getClap() {
        return clap;
    }

    public void setClap(int clap) {
        this.clap = clap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
                studentId == post.studentId &&
                courseId == post.courseId &&
                Objects.equals(text, post.text) &&
                clap == post.clap;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, studentId, courseId, text, clap);
    }
}
