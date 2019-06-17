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
    @Autowired
    PostService postService;

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Student> getStudents(){
        //This returns a JSON or XML with the students
        return studentRepository.findAll();
    }
    @GetMapping(path = "/login")
    public @ResponseBody Student login(@Valid Student student) {
        return studentService.login(student);
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


    //初始界面上显示一些课程供学生选择添加
    @GetMapping(path = "/showCourse")
    public @ResponseBody List<Course> all(@RequestParam int flag) {
        return courseService.getCourseByFlag(flag);
    }

    //学生加入一门课
    @PostMapping(path = "/joinCourse")
    public @ResponseBody boolean joinCourse(@RequestParam int studentID, int courseID) {
        return processService.initProcess(studentID, courseID);
    }

    //根据学生的id获取该学生选的所有课
    @GetMapping(path = "/allCourse")
    public @ResponseBody
    List<Course> allCourse(@RequestParam int studentID) {
        return courseService.getCourseByStudentID(studentID);
    }
    //查看学生是否有某门特定的课
    @GetMapping(path = "/hasCourse")
    public @ResponseBody
    Boolean hasCourse(@RequestParam  int courseID,@RequestParam int studentID){
        return processService.getUserProcess(courseID,studentID)!=null;
    }
    //根据具体课程的id获取具体的一门课
    @GetMapping(path = "/detailCourse")
    public @ResponseBody Course detailCourse(@RequestParam int courseID) {
        return courseService.getCourseByID(courseID);
    }


    //根据章节获取具体的学习内容(学生点击某一章，进入学习界面)
    @GetMapping(path = "/getContent")
    public @ResponseBody
    Content getContent(@RequestParam int studentID,@RequestParam String contentID ){
        //点击某一章内容的时候自动生成task
        taskService.generateUserTasks(studentID,contentID);
        return contentService.getContentByContentID(contentID);
    }

    //获取学生的学习进度（用途：1.老师查看学生学习进度2.学生继续学习接下来的内容3....）
    @GetMapping(path = "/getProcess")
    public @ResponseBody
    UserProcess getProcess(@RequestParam int studentID, @RequestParam int courseID) {
        return processService.getUserProcess(studentID, courseID);
    }


    //更新学生学习进度 学生退出后自动更新
    @GetMapping(path = "/updateProcess")
    public @ResponseBody boolean updateProcess(@RequestParam int studentID, int courseID, PresentNode presentNode, ProcessNode processNode) {
        return processService.modifyProcess(studentID, courseID, presentNode, processNode);
    }

    //查看学生所有任务
    @GetMapping(path = "/getTask")
    public @ResponseBody List<UserTasks> getTasks(@RequestParam int studentID) {
        return taskService.getUserTasksByStudentID(studentID);
    }

    //学生点击一个任务，显示任务
    @GetMapping(path = "/showTask")
    public @ResponseBody UserTasks showTasks(@RequestParam int taskID) {
        return taskService.getUserTasksByTaskID(taskID);
    }

    //学生答题
    @GetMapping(path = "/setAnswer")
    public boolean setAnswer(@RequestParam int studentID, @RequestParam String courseID,@RequestParam String answer) {
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

    //广场部分

    //学生发布一条帖子
    @PostMapping(path = "/addPost")
    public @ResponseBody Post addPost(@Valid Post post) {
        return postService.addPost(post);
    }

    //学生发布一条评论
    @PostMapping(path = "/addComment")
    public @ResponseBody Post addComment(@RequestParam int studentID, @RequestParam int postID, @RequestParam String text) {
        Student student=new Student();
        student.setStudentId(studentID);
        return postService.addComment(postID,text, studentService.getStudent(student));
    }

    //显示所有的帖子
    @PostMapping(path = "/showAllPost")
    public @ResponseBody List<Post> showAllPost(@RequestParam int courseID) {
        return postService.allPost(courseID);
    }

    //显示一条具体的帖子
    @PostMapping(path = "/showPost")
    public @ResponseBody Post showPost(@RequestParam int postID) {
        return postService.showPost(postID);
    }

    //给帖子点赞 返回值为成功之后帖子现在的点赞数
    @PostMapping(path = "/clap")
    public @ResponseBody int clap(@RequestParam int postID) {
        return postService.clap(postID);
    }

    //学生根据studentID查看自己的帖子
    @GetMapping(path = "/myPost")
    public @ResponseBody List<Post> myPost(@RequestParam int studentID) {
        return postService.myPost(studentID);
    }

    //学生删除一条帖子
    @PostMapping(path = "/deletePost")
    public @ResponseBody Post deletePost(@RequestParam int studentID, int postID) {
        return postService.deletePost(studentID, postID);
    }


}
