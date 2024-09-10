package com.example._20240904ordersysquardem00.config;

import com.example._20240904ordersysquardem00.service.CustomUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        String error = "帳號/密碼輸入錯誤";

        if (exception instanceof BadCredentialsException) {
            UserDetails userDetails = null;
            try {
                userDetails = userDetailsService.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                error = "無此帳號/密碼";
            }
            if (userDetails != null) {
                error = "帳號/密碼輸入錯誤";
            }
        }

        request.getSession().setAttribute("error", error);
        response.sendRedirect("/?error");
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;
}