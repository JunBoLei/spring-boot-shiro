package com.example.springbootshiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        System.out.println("in isAccessAllowed....");
//        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
//            return true;
//        }
//        return super.isAccessAllowed(request, response, mappedValue);
//    }


    /**
     * 当请求被拒绝时的处理方法（返回true表示需要继续处理，返回false表示不需要继续处理了）
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//
//        if (this.isLoginRequest(request, response)) {       //登陆请求
//            //是否登录请求
//            System.out.println("跳转登录");
//            if (this.isLoginSubmission(request, response)) {//直接登录提交
//                System.out.println("登录请求！！！！！");
//                return true;
//            } else { //业务请求接口被拦截
//                System.out.println("业务请求！！！！！");
//                Subject subject = SecurityUtils.getSubject();//获取当前线程的Subject
//                if (subject.isAuthenticated()) {
//                    System.out.println("已认证");
//                    return false;
//                } else {
//                    System.out.println("未认证");
//                    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//                    String Authorization = httpServletRequest.getHeader("Authorization");
//                    System.out.println("Authorization:" + Authorization);
//                    System.out.println("URI:" + httpServletRequest.getRequestURI());
//                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//                    httpServletResponse.setCharacterEncoding("UTF-8");
//                    httpServletResponse.setHeader("Content-type", "application/json; charset=utf-8");
//                    Writer writer = httpServletResponse.getWriter();
//                    Map<String, Object> result = new HashMap<>();
//                    result.put("msg", "未登录！！！");
//                    JSONObject responseJSONObject = new JSONObject(result.toString());
//                    writer.write(responseJSONObject.toString());
//                    writer.close();
//                    writer.flush();
//                   // return false;
//                }
//                return false;
//            }
//        } else {//非登录请求
//            System.out.println("一群水货。。。。。。。。。");
//            this.saveRequestAndRedirectToLogin(request, response);
//            return false;
//        }
//    }
}
