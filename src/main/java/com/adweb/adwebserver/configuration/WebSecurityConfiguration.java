package com.adweb.adwebserver.configuration;

import com.adweb.adwebserver.securityUtils.filter.JWTStudentFilter;
import com.adweb.adwebserver.securityUtils.provider.CustomAuthenticationProvider;
import com.adweb.adwebserver.securityUtils.filter.JWTAuthenticationFilter;
import com.adweb.adwebserver.securityUtils.filter.JWTTeacherFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 关闭csrf验证
        http.csrf().disable()
                // 对请求进行认证
                .authorizeRequests()
                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()
                // 角色检查 所有请求需要身份认证
                .antMatchers("/test").hasRole("TEACHER")
                .antMatchers("/students").hasRole("STUDENT")
                .antMatchers("/teachers").hasRole("TEACHER")
                .antMatchers("/students/showCourse").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/test/**").permitAll()
               // .antMatchers("/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTTeacherFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTTeacherFilter("/teachers/login", authenticationManager(),getApplicationContext()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTTeacherFilter("/teachers/register",authenticationManager(),getApplicationContext()),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTStudentFilter("/students/login",authenticationManager(),getApplicationContext()),UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
