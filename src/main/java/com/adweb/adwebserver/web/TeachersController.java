package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.*;
import com.adweb.adwebserver.domain.repository.TeacherRepository;
import com.adweb.adwebserver.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import javax.swing.*;
import javax.validation.Valid;
import java.io.IOException;
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
        //System.out.println("enter in the login");
        if (number == null || password == null) {
            return null;
        }
        return teacherService.login(number, password);
    }

    @PostMapping(path = "/update")
    public @ResponseBody
    Teacher updateInfo(@Valid Teacher teacher, @RequestParam(required = false) MultipartFile header) throws IOException {
        if (null != header) {
            teacher.setAvatar(fileService.uploadFile(header));
            System.out.println(teacher.toString());
        }
        return teacherService.update(teacher);
    }

    @GetMapping(path = "/info")
    public @ResponseBody
    Teacher getInfo(@RequestParam Integer teacherId) {
        if (teacherId == null) {
            return null;
        }
        System.out.println("enter the info");
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherId);
        return teacherService.getTeacher(teacher);
    }

    //todo set password need a check
    @PostMapping(path = "/password")
    public @ResponseBody
    boolean setPassword(@Valid Teacher teacher, @RequestParam String newPassword) {
        if (newPassword == null) {
            return false;
        }
        return teacherService.setPassword(teacher, newPassword);
    }

    //老师添加一门课程
    @PostMapping(path = "/createCourse")
    public @ResponseBody
    Course createCourse(@Valid Course course, @RequestParam(required = false) MultipartFile cover) throws IOException {
        if (null != cover) {
            System.out.println("enter the set cover");
            course.setCourseImage(fileService.uploadFile(cover));
        }
        System.out.println(course.toString());
        return courseService.addNewCourse(course);
    }

    //老师修改一门课程
    @PostMapping(path = "/modifyCourse")
    public @ResponseBody
    Course modifyCourse(@Valid Course course, @RequestParam(required = false) MultipartFile cover) throws IOException {
        if (null != cover) {
            System.out.println("enter the set cover");
            course.setCourseImage(fileService.uploadFile(cover));
        }
        System.out.println(course.toString());
        return courseService.modifyCourse(course);
    }

    //老师获取自己的一门课程
    //要根据teacherID来查吗 不然可能会查到不是自己的课程
    //解释，如果加上teacherID校验也没有必要，因为获得的course其实是公开的消息，对应的，应当在对课程进行编辑和删除处理的时候加上teacherID，这里可以不用了
    @GetMapping(path = "/getOneCourse")
    public @ResponseBody
    Course getOneCourse(@RequestParam Integer courseId) {
        if (courseId == null) {
            return null;
        }
        return courseService.getCourseByID(courseId);
    }

    //老师获取所有自己的课程
    @GetMapping(path = "/getAllCourse")
    public @ResponseBody
    List<Course> getAllCourse(@RequestParam Integer teacherId) {
        if (teacherId == null) {
            return null;
        }
        return teacherService.getAllCourseByTeacherID(teacherId);
    }


    //老师发布课程
    @PostMapping(path = "/postCourse")
    public @ResponseBody
    Course postCourse(@Valid Course course) {
        return courseService.postCourse(course);
    }

    //老师删除课程
    @PostMapping(path = "/deleteCourse")
    public @ResponseBody
    Course deleteCourse(@Valid Course course) {
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
    public @ResponseBody
    List<UserProcess> allProcess(@RequestParam Integer courseId, @RequestParam Integer teacherId) {
        return processService.getUserProcessesByCourseID(courseId, teacherId);
    }

    //老师查看一门课所有学生的任务
    // 是否需要根据teacherID来查 fixed
    @GetMapping(path = "/allTask")
    public @ResponseBody
    List<UserTasks> allTask(@RequestParam Integer courseId, @RequestParam Integer teacherId) {
        return taskService.getStudentsTasksByCourseID(courseId, teacherId);
    }

    //老师获取content
    @GetMapping(path = "/getContent")
    public @ResponseBody
    Content getContent(@RequestParam String contentId) {
        return contentService.getContentByContentID(contentId);
    }

    //老师添加content,这里json对象有点问题，就传字符串识别
    @PostMapping(path = "/addContent")
    public @ResponseBody
    Content addContent(@Valid Content content, @RequestParam(required = false) MultipartFile[] files, @RequestParam(required = false) String jDialog, @RequestParam(required = false) String jQuestion) throws IOException {
        contentCheck(content, files, jDialog, jQuestion);
        return contentService.addContent(content);
    }

    //老师修改content
    @PostMapping(path = "/modifyContent")
    public @ResponseBody
    Content modifyContent(@Valid Content content, @RequestParam(required = false) MultipartFile[] files, @RequestParam(required = false) String jDialog, @RequestParam(required = false) String jQuestion) throws IOException {
        contentCheck(content, files, jDialog, jQuestion);
        return contentService.modifyContent(content);
    }

    private void contentCheck(@Valid Content content, @RequestParam(required = false) MultipartFile[] files, @RequestParam(required = false) String jDialog, @RequestParam(required = false) String jQuestion) throws IOException {
        if (jDialog != null) {
            JSONArray dialog = JSONArray.parseArray(jDialog);
            content.setDialog(dialog);
        }
        if (jQuestion != null) {
            JSONObject question = JSONObject.parseObject(jQuestion);
            content.setQuestion(question);
        }
        modifyContentImage(content, files);
        System.out.println(content.toString());
    }

    //简单来就一个字符一个字符算了
    private void modifyContentImage(Content content, MultipartFile[] files) throws IOException {
        if (null != files && null != content) {
            int index = 0;
            int max = files.length;
            if (max==0)return;
            if (content.getDialog() != null) {
                JSONArray dialog = content.getDialog();
                for (int i = 0; i < dialog.size(); i++) {
                    JSONObject dialogNode = JSONObject.parseObject(dialog.getString(i));
                    if (1 == dialogNode.getInteger("kind")) {//1为图片的id属性
                        dialogNode.put("content", fileService.uploadFile(files[index]));
                        index += 1;
                    }
                    if (max == index) break;
                }
                content.setDialog(dialog);
            }
            if (content.getQuestion() != null) {
                JSONObject question = content.getQuestion();
                String[] images = new String[max - index];
                for (int i = index; i < max; i++) {
                    images[max - index - 1] = fileService.uploadFile(files[index]);
                }
                question.put("images", images);
                content.setQuestion(question);
            }
        }
    }

    //老师添加directory
    @PostMapping(path = "/addDirectory")
    public @ResponseBody
    Course addDirectory(@RequestParam Integer courseId, @RequestParam String jDirectory) {
        System.out.println(jDirectory);
        JSONObject directory=JSONObject.parseObject(jDirectory);
        return directoryService.addNewDirectory(courseId, directory);
    }

    //老师修改directory,废弃
    @PostMapping(path = "/modifyDirectory")
    public @ResponseBody
    Course modifyDirectory(@RequestParam Integer courseId, @RequestParam String jDirectory) {
//        JSONArray directory = JSONArray.parseArray(jDirectory);
//        return directoryService.modifyDirectory(courseId, directory);
        return null;
    }


}
