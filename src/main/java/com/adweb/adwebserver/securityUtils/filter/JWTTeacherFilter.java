package com.adweb.adwebserver.securityUtils.filter;

import com.adweb.adwebserver.domain.JSONResult;
import com.adweb.adwebserver.domain.Teacher;
import com.adweb.adwebserver.securityUtils.TokenAuthentication;
import com.adweb.adwebserver.service.TeacherService;
import com.adweb.adwebserver.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            System.out.println("Parameter Name - "+paramName+", Value - "+req.getParameter(paramName));
        }
        // 返回一个验证令牌
        String number=req.getParameter("number");
        String password=req.getParameter("password");
        if (number==null||password==null) return null;
        if (req.getRequestURI().contains("register")){
            System.out.println("teacher_register");
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
            System.out.println("teacher_login");
            if (teacherService.login(number,password)==null) return null;
        }
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
