package com.adweb.adwebserver.securityUtils.filter;

import com.adweb.adwebserver.domain.JSONResult;
import com.adweb.adwebserver.securityUtils.TokenAuthentication;
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

public class JWTStudentFilter extends AbstractAuthenticationProcessingFilter {
    public JWTStudentFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        // 返回一个验证令牌
        String number = req.getParameter("number");
        String wechatID = req.getParameter("wechatID");
        if (number == null || wechatID == null) return null;
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(number, wechatID)
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
