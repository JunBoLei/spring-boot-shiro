package com.example.springbootshiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.json.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;


public class ShiroFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 当请求(包括登录和访问业务接口)被拒绝时的处理方法（返回true表示需要继续处理，返回false表示不需要继续处理了）
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        if (this.isLoginRequest(request, response)) {       //登陆请求
            //是否登录请求
            System.out.println("跳转登录");
            if (this.isLoginSubmission(request, response)) {//直接登录提交
                System.out.println("登录请求！！！！！");
                return this.executeLogin(request, response);
            } else { //业务请求接口被拦截
                System.out.println("其他请求！！！！！");
                Subject subject = SecurityUtils.getSubject();//获取当前线程的Subject
                if (subject.isAuthenticated()) {
                    System.out.println("已认证");
                } else {
                    System.out.println("未认证");
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setCharacterEncoding("UTF-8");
                    httpServletResponse.setHeader("Content-type", "application/json; charset=utf-8");
                    Writer writer = httpServletResponse.getWriter();
                    Map<String,Object> result = new HashMap<>();
                    result.put("msg","未登录！！！");
                    JSONObject responseJSONObject = new JSONObject(result.toString());
                    writer.write(responseJSONObject.toString());
                    writer.close();
                    writer.flush();
                }
                return false;
            }
        }else {//非登录请求

            return true;
        }
    }
}
