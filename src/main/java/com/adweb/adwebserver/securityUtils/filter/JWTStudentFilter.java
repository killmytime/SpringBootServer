package com.adweb.adwebserver.securityUtils.filter;

import com.adweb.adwebserver.domain.JSONResult;
import com.adweb.adwebserver.domain.Student;
import com.adweb.adwebserver.securityUtils.TokenAuthentication;
import com.adweb.adwebserver.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class JWTStudentFilter extends AbstractAuthenticationProcessingFilter {
    StudentService studentService;
    public JWTStudentFilter(String url, AuthenticationManager authManager, ApplicationContext applicationContext) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.studentService=applicationContext.getBean(StudentService.class);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        // 返回一个验证令牌
        res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        String name = req.getParameter("name");
        String wechatID = req.getParameter("wechatID");
        String avatar=req.getParameter("avatar");
        if (name == null || wechatID == null||avatar==null) return null;
        Student student=new Student();
        student.setWechatId(wechatID);
        student.setAvatar(avatar);
        student.setName(name);
        studentService.login(student);
        int studentId=studentService.getStudentByWechatID(wechatID).getStudentId();
        System.out.println("studentId:"+studentId);
        res.setHeader("studentId", String.valueOf(studentId));
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(name, wechatID)
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) {
        TokenAuthentication.addAuthentication(res, auth.getName(),"ROLE_STUDENT");
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!", null));
    }
}
