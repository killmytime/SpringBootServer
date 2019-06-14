package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.*;
import com.adweb.adwebserver.domain.repository.StudentRepository;
import com.adweb.adwebserver.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/students")
public class StudentsController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    ContentService contentService;
    @Autowired
    ProcessService processService;
    @Autowired
    TaskService taskService;
    @Autowired
    NoteService noteService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Student> getStudents(){
        //This returns a JSON or XML with the students
        return studentRepository.findAll();
    }
    @GetMapping(path = "/login")
    public @ResponseBody Student login(@RequestParam String wechatId) {
        return studentService.login(wechatId);
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    Student update(@Valid Student student){
        return studentService.update(student);
    }

    @GetMapping(path = "/info")
    public @ResponseBody Student getInfo(@RequestParam int studentID){
        Student student = new Student();
        student.setStudentId(studentID);
        return studentService.getStudent(student);
    }

    //todo 这个getCourseByFlag是否有问题？已经说是获取所有已发布的课程，那应该直接找flag为1的课程，为什么要提供一个flag
    //初始界面上显示一些课程供学生选择添加
    @GetMapping(path = "/all")
    public @ResponseBody List<Course> all(@RequestParam int flag) {
        return courseService.getCourseByFlag(flag);
    }

    //学生加入一门课
    @PostMapping(path = "/joinCourse")
    public @ResponseBody boolean joinCourse(@RequestParam int studentID, int courseID) {
        //目前学生获得所有课程列表是通过枚举user_process表中的courseID，但是要加入新的课程，是通过加新的user_process吗？
        //还是CourseService中添加学生加入课程的方法？如果这样没法做 因为course表中没有学生的信息
        return processService.initProcess(studentID, courseID);
    }

    //根据学生的id获取该学生选的所有课
    @GetMapping(path = "/allCourse")
    public @ResponseBody
    List<Course> allCourse(@RequestParam int studentID) {
        return courseService.getCourseByStudentID(studentID);
    }

    //根据具体课程的id获取具体的一门课
    @GetMapping(path = "/detailCourse")
    public @ResponseBody Course detailCourse(@RequestParam int courseID) {
        return courseService.getCourseByID(courseID);
    }

    //todo 是否需要
    //更新学生学习课程的信息 比如学习进度等
    @GetMapping(path = "/updateCourse")
    public @ResponseBody Course updateCourse(@RequestParam int studentID, @RequestParam int courseID) {
        //return studentService.update(@Valid Course course)
        return null;
    }

    //根据章节获取具体的学习内容(学生点击某一章，进入学习界面)
    @GetMapping(path = "/getContent")
    public @ResponseBody
    Content getContent(@RequestParam String contentID ){
        return contentService.getContentByContentID(contentID);
    }

    //获取学生的学习进度（用途：1.老师查看学生学习进度2.学生继续学习接下来的内容3....）
    @GetMapping(path = "/getProcess")
    public @ResponseBody
    UserProcess getProcess(@RequestParam int studentID, @RequestParam int courseID) {
        return processService.getUserProcess(studentID, courseID);
    }

    //todo 初始化学习进度？
    //每加一门课就init学习进度，应该可以吧

    //更新学生学习进度 学生退出后自动更新
    //todo ProcessService中modifyProcess感觉要修改
    @GetMapping(path = "/updateProcess")
    public @ResponseBody boolean updateProcess(@RequestParam int studentID, int courseID, PresentNode presentNode, ProcessNode processNode) {
        return processService.modifyProcess(studentID, courseID, presentNode, processNode);
    }

    //todo 点击一个章节的时候生成任务？

    //查看学生所有任务
    @GetMapping(path = "/getTask")
    public @ResponseBody List<UserTasks> getTasks(@RequestParam int studentID) {
        return taskService.getUserTasksByStudentID(studentID);
    }

    //学生点击一个任务，显示任务
    @GetMapping(path = "/showTask")
    public @ResponseBody UserTasks showTasks(@RequestParam int taskID) {
        //return taskService.getUserTasksByTaskID(taskID);
        return null;
        //todo askService加一个getUserTaskByTaskID
        //getStudentsTasksByCourseID是否需要？获得一整门课的task用于什么场景？
    }

    //学生答题
    @GetMapping(path = "/setAnswer")
    public boolean setAnswer(@RequestParam int studentID, String courseID, String answer) {
        return taskService.setAnswer(studentID, courseID, answer);
    }

    //学生做笔记
    @GetMapping(path = "/addNote")
    public @ResponseBody UserNotes addNote(@Valid UserNotes userNotes) {
        return noteService.addNote(userNotes);
    }

    //学生修改笔记
    @GetMapping(path = "/modifyNote")
    public @ResponseBody UserNotes modifyNote(@Valid UserNotes userNotes) {
        return noteService.modifyNote(userNotes);
    }

    //查看所有笔记
    @GetMapping(path = "/allNotes")
    public @ResponseBody List<UserNotes> allNotes(@RequestParam int studentID) {
        return noteService.getNotesByStudentID(studentID);
    }



}
