package com.qf.shiro;

import com.qf.domain.Administrator;

import com.qf.service.AdministratorService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdministratorService administratorService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = (String) token.getPrincipal();
        Administrator adminByName = administratorService.findAdminByName(name);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, adminByName.getPass(), getName());
        return simpleAuthenticationInfo;
    }
}
