package com.adweb.adwebserver.domain;

import com.alibaba.fastjson.JSONArray;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;
@TypeDef(name = "json", typeClass = JsonStringType.class)
@Entity
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseDetail;
    private Integer teacherId;
    private String teacherName;
    private Integer flag;
    private JSONArray directory;
    private String courseImage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseID")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
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
    @Column(name = "course_detail")
    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    @Basic
    @Column(name = "teacherID")
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
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

    @Basic
    @Column(name = "flag")
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Basic
    @Type(type = "json")
    @Column(name = "directory")
    public JSONArray getDirectory() {
        return directory;
    }

    public void setDirectory(JSONArray directory) {
        this.directory = directory;
    }

    @Basic
    @Column(name = "course_image")
    public String getCourseImage(){return courseImage;}
    public void setCourseImage(String courseImage){this.courseImage=courseImage;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return courseId == course.courseId &&
                teacherId == course.teacherId &&
                flag == course.flag &&
                Objects.equals(courseName, course.courseName) &&
                Objects.equals(courseDetail, course.courseDetail) &&
                Objects.equals(teacherName, course.teacherName) &&
                Objects.equals(directory, course.directory)&&
                Objects.equals(courseImage,course.courseImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, courseName, courseDetail, teacherId, teacherName, flag, directory,courseImage);
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseDetail='" + courseDetail + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", flag=" + flag +
                ", directory=" + directory +
                ", courseImage='" + courseImage + '\'' +
                '}';
    }
}
