package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Content;
import com.adweb.adwebserver.domain.Course;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.UserProcess;
import com.adweb.adwebserver.domain.repository.TeacherRepository;
import com.adweb.adwebserver.service.ContentService;
import com.adweb.adwebserver.service.CourseService;
import com.adweb.adwebserver.service.ProcessService;
import com.adweb.adwebserver.service.TeacherService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/teachers")
public class TeachersController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    ProcessService processService;
    @Autowired
    ContentService contentService;
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Teacher> getTeachers() {
        //This returns a JSON or XML with the teachers
        return teacherRepository.findAll();
    }

    /**
     * 这里稍作解释，只要前端传过来的数据和后端对象的数据名字一致就可以全部解析了
     * 用框架真的是懒，不过自动化解析也就很舒服
     *
     * @param teacher 老师相关参数
     * @return teacher对象
     */
    @PostMapping(path = "/register")
    public @ResponseBody
    Teacher register(@Valid Teacher teacher) {
        //只要有手机号密码，名字，邀请码四要素即可
        return teacherService.register(teacher);
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    Teacher login(@RequestParam String number, @RequestParam String password) {
        System.out.println("enter in the login");
        return teacherService.login(number, password);
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    Teacher update(@Valid Teacher teacher) {
        return teacherService.update(teacher);
    }

    @GetMapping(path = "/info")
    public @ResponseBody
    Teacher getInfo(@RequestParam int teacherID) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherID);
        return teacherService.getTeacher(teacher);
    }

    @PostMapping(path = "/password")
    public @ResponseBody
    boolean setPassword(@Valid Teacher teacher, @RequestParam String newPassword) {
        return teacherService.setPassword(teacher, newPassword);
    }

    //老师添加一门课程
    @PostMapping(path = "/createCourse")
    public @ResponseBody
    Course createCourse(@Valid Course course) {
        return courseService.addNewCourse(course);
    }

    //老师修改一门课程
    @PostMapping(path = "/modifyCourse")
    public @ResponseBody
    Course modifyCourse(@Valid Course course) {
        return courseService.modifyCourse(course);
    }

    //老师获取所有自己的课程
    @GetMapping(path = "/getCourse")
    public @ResponseBody
    List<Course> getCourse(@RequestParam int teacherID) {
        return teacherService.getAllCourseByTeacherID(teacherID);
    }


    //老师发布课程
    @PostMapping(path = "/postCourse")
    @ResponseBody Course postCourse(@Valid Course course) {
        return courseService.postCourse(course);
    }

    //老师删除课程
    @PostMapping(path = "/deleteCourse")
    @ResponseBody Course deleteCourse(@Valid Course course) {
        return courseService.deleteCourse(course);
    }

    //获取学生的学习进度（用途：1.老师查看学生学习进度2.学生继续学习接下来的内容3....）
    @GetMapping(path = "/checkProcess")
    public @ResponseBody
    UserProcess getProcess(@RequestParam int studentID, @RequestParam int courseID) {
        return processService.getUserProcess(studentID, courseID);
    }
    //todo 要写一个只根据courseID查看进度的，然后返回一个list

    //todo 老师查看学生的任务

    //todo 需要写get|add|modify content、directory什么的吗 还是直接设置成添加后不能修改 简单一点？
    @PostMapping(path = "/modifyContent")
    Content modifyContent(@Valid Content content){
        return contentService.modifyContent(content);
    }

}
