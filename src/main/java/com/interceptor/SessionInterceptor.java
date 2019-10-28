package com.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.Writer;

public class SessionInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        System.out.println("开始请求地址拦截");
        HttpSession session = request.getSession(false);

        PrintWriter writer = response.getWriter();;

        if (session != null && session.getAttribute("user") != null)
        {
            System.out.println("success");
            writer.write("true");
            return true;
        }
        else
        {
            System.out.println("error");
            writer.write("false");
            return false;
        }
    }
}
