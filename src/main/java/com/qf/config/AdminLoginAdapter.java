package com.qf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class AdminLoginAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns为要拦截的，excludePathPatterns为要放行的。
        registry.addInterceptor(adminLoginInterceptor).addPathPatterns("").excludePathPatterns("");
        super.addInterceptors(registry);
    }
}
