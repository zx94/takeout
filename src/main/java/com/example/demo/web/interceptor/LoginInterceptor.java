package com.example.demo.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

/**
 * @description 登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取Session中的user_session的对象 如果有值，说明已登录，否则重定向到登陆页
        final Object obj = request.getSession().getAttribute(USER_SESSION_KEY);
        if (null != obj) return true;
        response.sendRedirect("/login");
        return false;
    }
}
