package com.adweb.adwebserver.securityUtils.provider;

import com.adweb.adwebserver.service.StudentService;
import com.adweb.adwebserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
// 自定义身份认证验证组件
//完善了静态注入部分的代码示例
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    private static CustomAuthenticationProvider customAuthenticationProvider;

    @PostConstruct
    public void init() {
        customAuthenticationProvider = this;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的手机号 & 密码
        String number = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 认证逻辑
        if (customAuthenticationProvider.teacherService.login(number,password)!=null){
            System.out.println(customAuthenticationProvider.teacherService.login(number,password).getNumber());
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(number, password, null);
        }else if (customAuthenticationProvider.studentService.login(password)!=null){//实际上对openID处理之后的值
            System.out.println(customAuthenticationProvider.studentService.login(password).getNumber());
            return new UsernamePasswordAuthenticationToken(number,password,null);

        }
            else {
            throw new BadCredentialsException("登陆信息有误，请重新登陆~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
