//package com.example.demo.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.web.DefaultRedirectStrategy;
//import org.springframework.security.web.RedirectStrategy;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class BrowserSecurityController {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    // 原请求信息的缓存及恢复
//    private RequestCache requestCache = new HttpSessionRequestCache();
//
//    // 用于重定向
//    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
//
//    /**
//     * 当需要身份认证的时候，跳转过来
//     * @param request
//     * @param response
//     * @return
//     */
//    @RequestMapping("/authentication/require")
//    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
//    public BaseResponse requireAuthenication(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        if (savedRequest != null) {
//            String targetUrl = savedRequest.getRedirectUrl();
//            logger.info("引发跳转的请求是:" + targetUrl);
//            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
//                redirectStrategy.sendRedirect(request, response, "/login.html");
//            }
//        }
//
//        return new BaseResponse("访问的服务需要身份认证，请引导用户到登录页");
//    }
//}
