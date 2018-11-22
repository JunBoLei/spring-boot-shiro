package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private MyReposory myReposory;
    @RequestMapping("/list")
    public Map<String,Object> list(){
        Map response =new HashMap<>();
        response.put("code","000");
        response.put("msg","登录成功，正常访问！");
        response.put("list",myReposory.list());
        return response;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("123456","123456");
        subject.login(usernamePasswordToken);
        Map response =new HashMap<>();
        response.put("token",subject.getSession().getId().toString().replace("-",""));
        //subject.logout();
        return response;
    }
}
