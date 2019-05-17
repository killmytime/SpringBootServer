package com.adweb.adwebserver.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
@Component
@Entity
public class Course {
    private int courseId;
    private String courseName;
    private int directoryId;
    private String courseDetail;
    private int teacherId;
    private String teacherName;

    @Id
    @Column(name = "courseID")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "directoryID")
    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }

    @Basic
    @Column(name = "course_detail")
    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    @Basic
    @Column(name = "teacherID")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "teacher_name")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId &&
                directoryId == course.directoryId &&
                teacherId == course.teacherId &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(courseDetail, course.courseDetail) &&
                Objects.equals(teacherName, course.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, directoryId, courseDetail, teacherId, teacherName);
    }
}
