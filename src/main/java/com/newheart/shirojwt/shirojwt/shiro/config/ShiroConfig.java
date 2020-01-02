package com.newheart.shirojwt.shirojwt.shiro.config;

import com.newheart.shirojwt.shirojwt.filter.JwtFilter;
import com.newheart.shirojwt.shirojwt.shiro.realm.LoginUserRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hanjie
 * @date 2019/12/30 16:51
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>(4);
        filterMap.put("jwt", new JwtFilter());
        factoryBean.setLoginUrl("/unauth");
        Map<String, String> filterRuleMap = new HashMap<>(4);
        filterRuleMap.put("/login/**", "anon");
        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");
        // 访问 /unauthorized/** 不通过JWTFilter
        // 配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        factoryBean.setLoginUrl("/unauth");
        factoryBean.setFilters(filterMap);
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }


    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(loginUserRealm());
//  http://shiro.apache.org/session-management.html# 禁用subject会话存储
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator storageEvaluator = new DefaultSessionStorageEvaluator();
        storageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(storageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    @Bean
    public Realm loginUserRealm(){
        return new LoginUserRealm();
    }

}
