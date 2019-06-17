package com.adweb.adwebserver.securityUtils.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
// 自定义身份认证验证组件
//完善了静态注入部分的代码示例
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的手机号 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 生成令牌
        return new UsernamePasswordAuthenticationToken(name, password, null);
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
