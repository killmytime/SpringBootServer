package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.*;
import com.adweb.adwebserver.domain.repository.TeacherRepository;
import com.adweb.adwebserver.service.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    TaskService taskService;
    @Autowired
    DirectoryService directoryService;
    @Autowired
    FileService fileService;
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
    Teacher update(@Valid Teacher teacher,@RequestParam MultipartFile header)throws IOException {
        if (header!=null) {
            teacher.setAvatar(fileService.uploadFile(header));//Todo 更新服务器的时候需要测一下
            System.out.println(teacher.toString());
        }
        return teacherService.update(teacher);
    }

    @GetMapping(path = "/info")
    public @ResponseBody
    Teacher getInfo(@RequestParam Integer teacherId) {
        System.out.println("enter the info");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
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

    //老师获取自己的一门课程
    //要根据teacherID来查吗 不然可能会查到不是自己的课程
    //解释，如果加上teacherID校验也没有必要，因为获得的course其实是公开的消息，对应的，应当在对课程进行编辑和删除处理的时候加上teacherID，这里可以不用了
    @GetMapping(path = "/getOneCourse")
    public @ResponseBody Course getOneCourse(@RequestParam Integer courseId) {
        return courseService.getCourseByID(courseId);
    }

    //老师获取所有自己的课程
    @GetMapping(path = "/getAllCourse")
    public @ResponseBody
    List<Course> getAllCourse(@RequestParam Integer teacherId) {
        return teacherService.getAllCourseByTeacherID(teacherId);
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
    UserProcess getProcess(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        return processService.getUserProcess(studentId, courseId);
    }

    //老师查看一门课程所有学生的学习进度
    // 是否需要根据teacherID来查 fixed
    @GetMapping(path = "/allProcess")
    public @ResponseBody List<UserProcess> allProcess(@RequestParam Integer courseId,@RequestParam Integer teacherId) {
        return processService.getUserProcessesByCourseID(courseId,teacherId);
    }

    //老师查看一门课所有学生的任务
    // 是否需要根据teacherID来查 fixed
    @GetMapping(path = "/allTask")
    public @ResponseBody List<UserTasks> allTask(@RequestParam Integer courseId,@RequestParam Integer teacherId) {
        return taskService.getStudentsTasksByCourseID(courseId,teacherId);
    }

    //老师获取content
    @GetMapping(path = "/getContent")
    public @ResponseBody Content getContent(@RequestParam String contentId) {
        return contentService.getContentByContentID(contentId);
    }

    //老师添加content
    @PostMapping(path = "/addContent")
    public @ResponseBody Content addContent(@Valid Content content,@RequestParam MultipartFile[] files) {
        return contentService.addContent(content);
    }

    //老师修改content
    @PostMapping(path = "/modifyContent")
    public @ResponseBody Content modifyContent(@Valid Content content) {
        return  contentService.modifyContent(content);
    }

    //老师添加directory
    @PostMapping(path = "/addDirectory")
    public @ResponseBody Course addDirectory(@RequestParam Integer courseId, @RequestParam JSONArray list) {
        return directoryService.addNewDirectory(courseId, list);
    }

    //老师修改directory
    @PostMapping(path = "/modifyDirectory")
    public @ResponseBody Course modifyDirectory(@RequestParam Integer courseId, @RequestParam JSONArray list) {
        return directoryService.modifyDirectory(courseId, list);
    }


}
