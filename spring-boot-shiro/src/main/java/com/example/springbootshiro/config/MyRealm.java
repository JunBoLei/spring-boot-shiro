package com.example.springbootshiro.config;

import com.example.springbootshiro.controller.MyReposory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 自定义认证类
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private MyReposory myReposory;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 当执行subject.login()时调用，返回用户信息不为空，shiro认为登陆成功.如果为空,shiro认为登录失败
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            UsernamePasswordToken token1 = (UsernamePasswordToken) token;
            List<Map<String,Object>> list = myReposory.login(token1.getUsername(),new String(token1.getPassword())+"");
            System.out.println("username:"+token1.getUsername());
            System.out.println("password:"+new String(token1.getPassword())+"");
            if (list == null || list.size() == 0){
                return null;
            }
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(list.get(0).get("username"), list.get(0).get("password"),getName());
            return authcInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NullPointerException("查询用户不存在");
        }
    }
}
