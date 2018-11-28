package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public Map<String,Object> login(@RequestBody Map<String,Object> map){
        Map response =new HashMap<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(map.get("username")+"",map.get("password")+"");
            subject.login(usernamePasswordToken);
            response.put("token",subject.getSession().getId().toString().replace("-",""));
            response.put("code","000");
            response.put("msg","登录成功");
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("code","500");
            response.put("msg","登录失败");
            return response;
        }
    }

    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "403");
        map.put("msg", "未登录");
        return map;
    }
}
