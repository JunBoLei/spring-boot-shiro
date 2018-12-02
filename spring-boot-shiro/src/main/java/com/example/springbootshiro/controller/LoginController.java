package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private MyReposory myReposory;

//    @Autowired
//    private ;
    @RequestMapping("/list")
    public Map<String,Object> list(@RequestBody(required=false) Map<String,Object> map){
        Map response =new HashMap<>();
        response.put("code","000");
        response.put("msg","登录成功，正常访问！");
        response.put("list",myReposory.list());
        return response;
    }

    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody(required = false) Map<String,Object> map,HttpServletResponse httpServletResponse){
        Map response =new HashMap<>();
        System.out.println(map);
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(map.get("username")+"",map.get("password")+"");
            subject.login(usernamePasswordToken);
            List<Map<String,Object>> list = myReposory.login(map.get("username")+"",map.get("password")+"");
            String id = UUID.randomUUID().toString().replace("-","");
            Integer userId = Integer.parseInt(list.get(0).get("id")+"");
            String token = UUID.randomUUID().toString().replace("-","");
            myReposory.saveUserToken(id,userId,token);
            response.put("token",token);
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
        map.put("msg", "未授权");
        return map;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout() {
        Map response =new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        response.put("code", "000");
        response.put("msg", "退出成功");
        return response;
    }
}
