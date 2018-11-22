package com.example.springbootshiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);//securityManager必须设置
        shiroFilterFactoryBean.setLoginUrl("/login");//登录路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");//未授权路径
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login","anon");//匿名访问
        filterChainDefinitionMap.put("/**", "authc");//拦截其他所有的请求

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //System.out.println("Shiro拦截器工厂类注入成功");
        System.out.println("++++++++++++++++++++++++++shiroconfig end++++++++++++++++++++++++++++");
        return shiroFilterFactoryBean;

    }

    /**
     * 注入securityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    /**
     * 注入认证类
     * @return
     */
    @Bean
    public MyRealm getRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
}
