package com.example.springbootshiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);//securityManager必须设置
        Map<String,Filter> filterMap = new LinkedHashMap<>();//过滤器
        filterMap.put("sessionFilter",getShiroFormAuthenticationFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/login");
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();//拦截设置

        filterChainDefinitionMap.put("/login","anon");//匿名访问
        filterChainDefinitionMap.put("/logout","anon");//匿名访问
        filterChainDefinitionMap.put("/**", "authc");//拦截其他所有的请求
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    /**
     * 注入securityManager
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());//设置认证类
        DefaultWebSessionManager defaultWebSessionManager = getDefaultWebSessionManager();//设置session管理类
        defaultWebSessionManager.setGlobalSessionTimeout(20000);//设置session有效期5秒
        securityManager.setSessionManager(defaultWebSessionManager);
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

    /**
     * 注入登录拦截器
     * @return
     */
    @Bean
    public ShiroFormAuthenticationFilter getShiroFormAuthenticationFilter(){
        return new ShiroFormAuthenticationFilter();
    }

    /**
     * 注入session管理类
     * @return
     */
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        return new DefaultWebSessionManager();
    }
}
