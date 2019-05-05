package com.example.demo.config;

import com.example.demo.web.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//TODO:拦截器
//WebMvc配置 包括拦截器和静态资源映射
@Configuration
public class WebMvcCfg implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    //配置拦截器
    //addPathPatterns 表示拦截,excludePathPatterns 表示不拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/static/**");
    }

}
