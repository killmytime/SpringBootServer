package com.adweb.adwebserver.web;

import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.domain.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/teachers")
public class TeachersController {
    @Autowired
    private TeacherRepository teacherRepository;
    @GetMapping(path="/add")
    public @ResponseBody String addNewTeacher(@RequestParam String name,@RequestParam String email){
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
}
