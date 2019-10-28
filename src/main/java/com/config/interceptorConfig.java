package com.config;

import com.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class interceptorConfig implements WebMvcConfigurer
{
    //需要拦截的地址
//    final String[] InterceptPaths = {"/backCommentInfo","/updateLikeCount", "/updateCount/**","/reduceCount/**","/reduceLikeCount"};


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
//        registry.addInterceptor(new SessionInterceptor()).addPathPatterns(InterceptPaths);
    }
}
