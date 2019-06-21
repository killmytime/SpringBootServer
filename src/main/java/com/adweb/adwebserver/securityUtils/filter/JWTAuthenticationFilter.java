package com.adweb.adwebserver.securityUtils.filter;

import com.adweb.adwebserver.securityUtils.TokenAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest= (HttpServletRequest) request;
//        Enumeration names = servletRequest.getHeaderNames();
//        System.out.println("===================================================================");
//        while(names.hasMoreElements()){
//            String name = (String) names.nextElement();
//            System.out.println(name + ":" + servletRequest.getHeader(name));
//        }
//        System.out.println("===================================================================");
//        System.out.println(servletRequest.getHeader("authorization"));
        HttpServletResponse servletResponse=(HttpServletResponse)response;
        if (servletRequest.getMethod().equals("OPTIONS")){
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            servletResponse.setHeader("Access-Control-Allow-Origin",servletRequest.getHeader("Origin"));
            servletResponse.setHeader("Access-Control-Allow-Headers","*");
        }
        else {
        servletResponse.setHeader("Access-Control-Allow-Origin",servletRequest.getHeader("Origin"));
        servletResponse.setHeader("Access-Control-Allow-Headers", "*");
        Authentication authentication = TokenAuthentication
                .getAuthentication(servletRequest);
        //((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin",((HttpServletRequest)request).getHeader("Origin"));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);}
    }
}
