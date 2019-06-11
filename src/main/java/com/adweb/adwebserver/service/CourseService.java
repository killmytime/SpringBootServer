package com.adweb.adwebserver.service;

import com.adweb.adwebserver.domain.Course;

import java.util.List;

public interface CourseService {
    Course addNewCourse(Course course);//这里courseID自动生成，之后需要定位用的都是courseID，返回courseID，状态设置忽略
    Course modifyCourse(Course course);//这里需要courseID来定位,修改课程的除内容之外的信息，状态设置忽略
    /**信息会处理掉一部分多余的，也可能忘记了，这是显示到主界面上的**/
    List<Course> getCourseByFlag(int flag);//这里期获取所有发布的课程，考虑到可能更改的需求，用flag指代，发布了的为1，没发布的为0，其他状态待定吧
    List<Course> getCourseByStudentID(int studentID);//这里完善的是获取学生的课程列表功能
    Course getCourseByID(int courseID);//这里根据courseID获取指定课程的详情内容
    Course postCourse(Course course);//这里需要的是courseID和flag，主要是flag，点击发布即设置为1
    Course deleteCourse(Course course);//这里需要courseID来定位，校验flag，仅可以删除未发布的课程，同时删除课程内容，这样不涉及用户信息的丢失问题
}
