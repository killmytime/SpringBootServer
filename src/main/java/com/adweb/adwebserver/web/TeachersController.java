package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.TeacherRepository;
import com.adweb.adwebserver.service.ServiceFactory;
import com.adweb.adwebserver.service.TeacherService;
import com.adweb.adwebserver.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/teachers")
public class TeachersController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    TeacherService teacherService;
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Teacher> getTeachers(){
        //This returns a JSON or XML with the teachers
        return teacherRepository.findAll();
    }
    /**
     * 这里稍作解释，只要前端传过来的数据和后端对象的数据名字一致就可以全部解析了
     * 用框架真的是懒，不过自动化解析也就很舒服
     * @param teacher 老师相关参数
     * @return
     */
    @PostMapping(path = "/register")
    public @ResponseBody Teacher register(@Valid Teacher teacher){
        return teacherService.register(teacher);
    }
    @GetMapping(path = "/login")
    public @ResponseBody Teacher login(@RequestParam int number,@RequestParam String password){
        return teacherService.login(number,password);
    }
    @PostMapping(path = "/update")
    public @ResponseBody Teacher update(@Valid Teacher teacher){
        return teacherService.update(teacher);
    }
    @GetMapping(path = "/info")
    public @ResponseBody Teacher getInfo(@RequestParam int teacherID){
        Teacher teacher=new Teacher();
        teacher.setTeacherId(teacherID);
        return teacherService.getTeacher(teacher);
    }
    @PostMapping(path = "/password")
    public @ResponseBody boolean setPassword(@Valid Teacher teacher,@RequestParam String newPassword){
        return teacherService.setPassword(teacher,newPassword);
    }

}
