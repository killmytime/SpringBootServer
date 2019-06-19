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
    public @ResponseBody Student getInfo(@RequestParam int studentId){
        Student student = new Student();
        student.setStudentId(studentId);
        return studentService.getStudent(student);
    }


    //初始界面上显示一些课程供学生选择添加
    @GetMapping(path = "/showCourse")
    public @ResponseBody List<Course> all(@RequestParam int flag) {
        return courseService.getCourseByFlag(flag);
    }

    //学生加入一门课
    @PostMapping(path = "/joinCourse")
    public @ResponseBody boolean joinCourse(@RequestParam int studentId, int courseId) {
        return processService.initProcess(studentId, courseId);
    }

    //根据学生的id获取该学生选的所有课
    @GetMapping(path = "/allCourse")
    public @ResponseBody
    List<Course> allCourse(@RequestParam int studentId) {
        return courseService.getCourseByStudentID(studentId);
    }
    //查看学生是否有某门特定的课
    @GetMapping(path = "/hasCourse")
    public @ResponseBody
    Boolean hasCourse(@RequestParam  int courseId,@RequestParam int studentId){
        return processService.getUserProcess(courseId,studentId)!=null;
    }
    //根据具体课程的id获取具体的一门课
    @GetMapping(path = "/detailCourse")
    public @ResponseBody Course detailCourse(@RequestParam int courseId) {
        return courseService.getCourseByID(courseId);
    }


    //根据章节获取具体的学习内容(学生点击某一章，进入学习界面)
    @GetMapping(path = "/getContent")
    public @ResponseBody
    Content getContent(@RequestParam int studentId,@RequestParam String contentId ){
        //点击某一章内容的时候自动生成task
        taskService.generateUserTasks(studentId,contentId);
        return contentService.getContentByContentID(contentId);
    }

    //获取学生的学习进度（用途：1.老师查看学生学习进度2.学生继续学习接下来的内容3....）
    @GetMapping(path = "/getProcess")
    public @ResponseBody
    UserProcess getProcess(@RequestParam int studentId, @RequestParam int courseId) {
        return processService.getUserProcess(studentId, courseId);
    }


    //更新学生学习进度 学生退出后自动更新
    @GetMapping(path = "/updateProcess")
    public @ResponseBody boolean updateProcess(@RequestParam int studentId, int courseId, PresentNode presentNode, ProcessNode processNode) {
        return processService.modifyProcess(studentId, courseId, presentNode, processNode);
    }

    //查看学生所有任务
    @GetMapping(path = "/getTask")
    public @ResponseBody List<UserTasks> getTasks(@RequestParam int studentId) {
        return taskService.getUserTasksByStudentID(studentId);
    }

    //学生点击一个任务，显示任务
    @GetMapping(path = "/showTask")
    public @ResponseBody UserTasks showTasks(@RequestParam int taskId) {
        return taskService.getUserTasksByTaskID(taskId);
    }

    //学生答题
    @GetMapping(path = "/setAnswer")
    public boolean setAnswer(@RequestParam int studentId, @RequestParam String courseId,@RequestParam String answer) {
        return taskService.setAnswer(studentId, courseId, answer);
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
    public @ResponseBody List<UserNotes> allNotes(@RequestParam int studentId) {
        return noteService.getNotesByStudentID(studentId);
    }

    //广场部分

    //学生发布一条帖子
    @PostMapping(path = "/addPost")
    public @ResponseBody Post addPost(@Valid Post post) {
        return postService.addPost(post);
    }

    //学生发布一条评论
    @PostMapping(path = "/addComment")
    public @ResponseBody Post addComment(@RequestParam int studentId, @RequestParam int postId, @RequestParam String text) {
        Student student=new Student();
        student.setStudentId(studentId);
        return postService.addComment(postId,text, studentService.getStudent(student));
    }

    //显示所有的帖子
    @PostMapping(path = "/showAllPost")
    public @ResponseBody List<Post> showAllPost(@RequestParam int courseId) {
        return postService.allPost(courseId);
    }

    //显示一条具体的帖子
    @PostMapping(path = "/showPost")
    public @ResponseBody Post showPost(@RequestParam int postId) {
        return postService.showPost(postId);
    }

    //给帖子点赞 返回值为成功之后帖子现在的点赞数
    @PostMapping(path = "/clap")
    public @ResponseBody int clap(@RequestParam int postId) {
        return postService.clap(postId);
    }

    //学生根据studentID查看自己的帖子
    @GetMapping(path = "/myPost")
    public @ResponseBody List<Post> myPost(@RequestParam int studentId) {
        return postService.myPost(studentId);
    }

    //学生删除一条帖子
    @PostMapping(path = "/deletePost")
    public @ResponseBody Post deletePost(@RequestParam int studentId, int postId) {
        return postService.deletePost(studentId, postId);
    }


}
