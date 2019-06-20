package com.adweb.adwebserver.securityUtils.filter;

import com.adweb.adwebserver.domain.JSONResult;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.securityUtils.TokenAuthentication;
import com.adweb.adwebserver.service.TeacherService;
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

/**
 *
 */
public class JWTTeacherFilter extends AbstractAuthenticationProcessingFilter {
    TeacherService teacherService;
    private final String INVITATION="ENJOY";

    public JWTTeacherFilter(String url, AuthenticationManager authManager, ApplicationContext applicationContext) {
        super(new AntPathRequestMatcher(url));
        this.teacherService=applicationContext.getBean(TeacherService.class);
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        // 返回一个验证令牌
        if (req.getMethod().equals("OPTIONS")){
            res.setStatus(HttpServletResponse.SC_OK);
            res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
            res.setHeader("Access-Control-Allow-Headers","*");
            return null;
        }
        res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        res.setHeader("Access-Control-Expose-Headers","teacherId");
        String number=req.getParameter("number");
        String password=req.getParameter("password");
        if (number==null||password==null) return null;
        if (req.getRequestURI().contains("register")){
            String invitation=req.getParameter("invitation");
            if (!invitation.equals(INVITATION)) return null;
            String name=req.getParameter("name");
            if (name==null) return null;
            Teacher teacher=new Teacher();
            teacher.setNumber(number);
            teacher.setPassword(password);
            teacher.setName(name);
            teacher.setInvitation(invitation);
            if (teacherService.register(teacher)==null) return null;
        }
        if (req.getRequestURI().contains("login")){
            if (teacherService.login(number,password)==null) return null;
        }
        int teacherId=teacherService.getTeacherIdByNumberAndPassword(number,password);
        res.setHeader("teacherId", String.valueOf(teacherId));
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(number,password)
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) {
        TokenAuthentication.addAuthentication(res, auth.getName(),"ROLE_TEACHER");
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JSONResult.fillResultString(500, "Internal Server Error!!!",null));
    }
}
