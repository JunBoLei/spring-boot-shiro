package com.example.springbootshiro.config;

import com.example.springbootshiro.controller.MyReposory;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     *
     * @param
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        if (authenticationToken == null) {
            throw new IllegalArgumentException("Method argumet (authentication token) cannot be null.");
        }
        String username = (String) authenticationToken.getPrincipal();  //得到用户名
        String password = new String((char[]) authenticationToken.getCredentials()); //得到密码

        System.out.println("repository>>>>>>>>>>>>>>" + myReposory);
        System.out.println("token>>>>>>>>>>>>>>" + authenticationToken);
        List<Map<String,Object>> list = myReposory.login(username,password);
        System.out.println(list);
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("用户名或密码错误");
        }
//        String id = UUID.randomUUID().toString().replace("-","");
//        Integer userId = Integer.parseInt(list.get(0).get("id")+"");
//        String token = UUID.randomUUID().toString().replace("-","");
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(list.get(0).get("username"), list.get(0).get("password"), getName());
        //myReposory.saveUserToken(id,userId,token);
        System.out.println(authcInfo);
        return authcInfo;
    }
}
