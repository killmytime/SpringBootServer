package com.adweb.adwebserver.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Component
@Entity
public class Course {
    private int courseId;
    private String courseName;
    private String courseDetail;
    private String coursePath;
    private int teacherId;
    private String teacherName;
    private int directoryId;

    @Id
    @Column(name = "CourseID")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "CourseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "CourseDetail")
    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    @Basic
    @Column(name = "CoursePath")
    public String getCoursePath() {
        return coursePath;
    }

    public void setCoursePath(String coursePath) {
        this.coursePath = coursePath;
    }

    @Basic
    @Column(name = "TeacherID")
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "TeacherName")
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
                teacherId == course.teacherId &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(courseDetail, course.courseDetail) &&
                Objects.equals(coursePath, course.coursePath) &&
                Objects.equals(teacherName, course.teacherName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDetail, coursePath, teacherId, teacherName);
    }

    @Basic
    @Column(name = "DirectoryID")
    public int getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(int directoryId) {
        this.directoryId = directoryId;
    }
}
