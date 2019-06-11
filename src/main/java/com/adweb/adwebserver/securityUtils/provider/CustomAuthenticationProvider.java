package com.adweb.adwebserver.securityUtils.provider;

import com.adweb.adwebserver.domain.repository.TeacherRepository;
import com.adweb.adwebserver.securityUtils.Authority;
import com.adweb.adwebserver.service.TeacherService;
import com.adweb.adwebserver.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
@Component
// 自定义身份认证验证组件
//完善了静态注入部分的代码示例
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    TeacherService teacherService;
    private static CustomAuthenticationProvider customAuthenticationProvider;

    @PostConstruct
    public void init() {
        customAuthenticationProvider = this;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String number = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 认证逻辑
        System.out.println(customAuthenticationProvider.teacherService.login(number,password).getNumber());
        if (customAuthenticationProvider.teacherService.login(number,password)!=null){
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new Authority("ROLE_TEACHER") );
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(number, password, authorities);
        } else {
            throw new BadCredentialsException("密码错误~");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
