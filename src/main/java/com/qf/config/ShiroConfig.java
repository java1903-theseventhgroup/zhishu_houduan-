package com.qf.config;

import com.qf.shiro.MyRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String ,String> map  = new HashMap<>();
       map.put("/main","authc");  //必须登录才可访问
        map.put("/addadmin","authc");  //必须登录才可访问
        map.put("/addcourse","authc");
        map.put("/addebook","authc");
        map.put("/addlisten","authc");
        map.put("/assignPermission","authc");
        map.put("/course","authc");
        map.put("/ebook","authc");
        map.put("/edit","authc");
        map.put("/form","authc");
        map.put("/role","authc");
        map.put("/listen","authc");
        map.put("/updateadmin","authc");
        map.put("/updatebook","authc");
        map.put("/updatecourse","authc");
        map.put("/updatelisten","authc");
        map.put("/updateuser","authc");
        map.put("/user","authc");
        shiroFilterFactoryBean.setLoginUrl("/login");//设置登录页（匿名）
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);//装配拦截策略
        return shiroFilterFactoryBean;
    }
    // 配置安全管理器（注入Realm对象）
    @Bean(name="defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myShiroRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm);
        return defaultWebSecurityManager;
    }


}
