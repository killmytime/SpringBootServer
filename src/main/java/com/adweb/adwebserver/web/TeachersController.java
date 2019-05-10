package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/teachers")
public class TeachersController {
    @Autowired
    private TeacherRepository teacherRepository;
    /**
     *
     * @param name 老师昵称
     * @param email 老师邮件
     * @param password 老师密码
     * @param invitation 老师邀请码 暂定为wearesocool，之后会考虑取手机号加密之后按照某种规则选取，用作资历认证机制，信息之后可能有待补充
     * @return
     */

    @GetMapping(path="/register")
    public @ResponseBody String addNewTeacher(@RequestParam String name,@RequestParam String email,@RequestParam String password,@RequestParam String invitation){
        Teacher teacher=new Teacher();
        teacher.setEmail(email);
        teacher.setName(name);
        teacherRepository.save(teacher);
        return "saved";
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Teacher> getTeachers(){
        //This returns a JSON or XML with the teachers
        return teacherRepository.findAll();
    }

    /**
     * 这里稍作解释，只要前端传过来的数据和后端对象的数据名字一致就可以全部解析了
     * 用框架真的是懒，不过自动化解析也就很舒服
     * @param teacher
     * @return
     */
    @PostMapping(path = "/registerTest")
    public @ResponseBody String test(@Valid Teacher teacher){
        return teacher.toString();
    }
}
