package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.*;
import com.adweb.adwebserver.domain.repository.StudentRepository;
import com.adweb.adwebserver.service.*;
import com.alibaba.fastjson.JSONArray;
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
    //这里由于一些历史遗留问题，不改了，，，
    @GetMapping(path = "/login")
    public @ResponseBody Student login(@Valid Student student) {
        if (student.getStudentId() == null) {
            return null;
        }

        return studentService.login(student);
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    Student update(@Valid Student student){
        if (student.getStudentId() == null) {
            return null;
        }
        return studentService.update(student);
    }

    @GetMapping(path = "/info")
    public @ResponseBody Student getInfo(@RequestParam Integer studentId){
        if (studentId == null) {
            return null;
        }
        Student student = new Student();
        student.setStudentId(studentId);
        return studentService.getStudent(student);
    }


    //初始界面上显示一些课程供学生选择添加
    @GetMapping(path = "/showCourse")
    public @ResponseBody List<Course> all(@RequestParam Integer flag) {
        if (flag == null) {
            return null;
        }
        return courseService.getCourseByFlag(flag);
    }

    //学生加入一门课
    @PostMapping(path = "/joinCourse")
    public @ResponseBody boolean joinCourse(@RequestParam Integer studentId, Integer courseId) {
        if (studentId == null || courseId == null) {
            return false;
        }
        return processService.initProcess(studentId, courseId);
    }

    //根据学生的id获取该学生选的所有课
    @GetMapping(path = "/allCourse")
    public @ResponseBody
    List<Course> allCourse(@RequestParam Integer studentId) {
        if (studentId == null) {
            return null;
        }
        return courseService.getCourseByStudentID(studentId);
    }
    //查看学生是否有某门特定的课
    @GetMapping(path = "/hasCourse")
    public @ResponseBody
    Boolean hasCourse(@RequestParam  Integer courseId,@RequestParam Integer studentId){
        if (courseId == null || studentId == null) {
            return null;
        }
        return processService.getUserProcess(courseId,studentId)!=null;
    }
    //根据具体课程的id获取具体的一门课
    @GetMapping(path = "/detailCourse")
    public @ResponseBody Course detailCourse(@RequestParam Integer courseId) {
        if (courseId == null) {
            return null;
        }
        return courseService.getCourseByID(courseId);
    }


    //根据章节获取具体的学习内容(学生点击某一章，进入学习界面)
    @GetMapping(path = "/getContent")
    public @ResponseBody
    Content getContent(@RequestParam int studentId,@RequestParam String contentId ){
        if (contentId == null) {
            return null;
        }
        //点击某一章内容的时候自动生成task
        taskService.generateUserTasks(studentId,contentId);
        return contentService.getContentByContentID(contentId);
    }

    //获取学生的学习进度（用途：1.老师查看学生学习进度2.学生继续学习接下来的内容3....）
    @GetMapping(path = "/getProcess")
    public @ResponseBody
    UserProcess getProcess(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        if (studentId == null || courseId == null) {
            return  null;
        }
        return processService.getUserProcess(studentId, courseId);
    }


    //更新学生学习进度 学生退出后自动更新
    @PostMapping(path = "/updateProcess")
    public @ResponseBody boolean updateProcess(@RequestParam Integer studentId, Integer courseId, String contentId,boolean isFinished,Integer dialogId) {
        if (studentId == null || courseId == null || contentId == null || dialogId == null) {
            return false;
        }
        PresentNode presentNode=new PresentNode();
        presentNode.setContentId(contentId);
        presentNode.setDialogId(dialogId);
        ProcessNode processNode=new ProcessNode();
        processNode.setContentId(contentId);
        processNode.setFinished(isFinished);
        return processService.modifyProcess(studentId, courseId, presentNode, processNode);
    }

    //查看学生所有任务
    @GetMapping(path = "/getTask")
    public @ResponseBody List<UserTasks> getTasks(@RequestParam Integer studentId) {
        if (studentId == null) {
            return null;
        }
        return taskService.getUserTasksByStudentID(studentId);
    }

    //学生点击一个任务，显示任务
    @GetMapping(path = "/showTask")
    public @ResponseBody UserTasks showTasks(@RequestParam Integer taskId) {
        if (taskId == null) {
            return null;
        }
        return taskService.getUserTasksByTaskID(taskId);
    }

    //学生答题
    @PostMapping(path = "/setAnswer")
    public boolean setAnswer(@RequestParam Integer studentId, @RequestParam String courseId,@RequestParam String answer) {
        if (studentId == null || courseId == null || answer == null) {
            return false;
        }
        return taskService.setAnswer(studentId, courseId, answer);
    }

    //学生做笔记
    @PostMapping(path = "/addNote")
    public @ResponseBody UserNotes addNote(@Valid UserNotes userNotes) {
        if (userNotes == null) {
            return null;
        }
        return noteService.addNote(userNotes);
    }

    //学生修改笔记
    @PostMapping(path = "/modifyNote")
    public @ResponseBody UserNotes modifyNote(@Valid UserNotes userNotes) {
        if (userNotes == null) {
            return null;
        }
        return noteService.modifyNote(userNotes);
    }

    //查看所有笔记
    @GetMapping(path = "/allNotes")
    public @ResponseBody List<UserNotes> allNotes(@RequestParam Integer studentId) {
        if (studentId == null) {
            return null;
        }
        return noteService.getNotesByStudentID(studentId);
    }

    //广场部分

    //学生发布一条帖子
    @PostMapping(path = "/addPost")
    public @ResponseBody Post addPost(@Valid Post post,@RequestParam String jText) {
        if(post==null||jText==null) return null;
        System.out.println(jText);
        JSONArray text=JSONArray.parseArray(jText);
        post.setText(text);
        return postService.addPost(post);
    }

    //学生发布一条评论
    @PostMapping(path = "/addComment")
    public @ResponseBody Post addComment(@RequestParam Integer studentId, @RequestParam Integer postId, @RequestParam String text) {
        if (studentId == null || postId == null || text == null) {
            return null;
        }
        Student student=new Student();
        student.setStudentId(studentId);
        return postService.addComment(postId,text, studentService.getStudent(student));
    }

    //显示所有课程的帖子
    @GetMapping(path = "/showAllPost")
    public @ResponseBody List<Post> showAllPost() {
        return postService.getAllPost();
    }
    //显示所有课程的帖子

    //显示一条具体的帖子
    @GetMapping(path = "/showPost")
    public @ResponseBody Post showPost(@RequestParam Integer postId) {
        if (postId == null) {
            return null;
        }
        return postService.showPost(postId);
    }

    //给帖子点赞 返回值为成功之后帖子现在的点赞数
    @PostMapping(path = "/clap")
    public @ResponseBody int clap(@RequestParam Integer postId) {
        if (postId == null) {
            return -1;
        }
        return postService.clap(postId);
    }

    //学生根据studentID查看自己的帖子
    @GetMapping(path = "/myPost")
    public @ResponseBody List<Post> myPost(@RequestParam Integer studentId) {
        if (studentId == null) {
            return null;
        }
        return postService.myPost(studentId);
    }

    //学生删除一条帖子
    @PostMapping(path = "/deletePost")
    public @ResponseBody Post deletePost(@RequestParam Integer studentId,@RequestParam Integer postId) {
        if (studentId == null || postId == null) {
            return null;
        }
        return postService.deletePost(studentId, postId);
    }


}
