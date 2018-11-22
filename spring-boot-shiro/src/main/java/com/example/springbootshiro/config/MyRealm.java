package com.example.springbootshiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义认证类
 */
public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("goGet1.................................");
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
        System.out.println("goGet2.................................");
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("123456", "123456",getName());
        return authcInfo;
    }
}
