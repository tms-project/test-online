package com.example.testonline.shiro.realm;

import com.example.testonline.pojo.Administrator;
import com.example.testonline.service.AdminService;
import com.example.testonline.shiro.CostomLoginToken;
import com.example.testonline.shiro.LoginType;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class AdminRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principalCollection.getRealmNames().contains("admin")){
            info.addRole("admin");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CostomLoginToken token = (CostomLoginToken) authenticationToken;
        Administrator administrator = adminService.select(token.getUsername());

        if (administrator == null)
            throw new UnknownAccountException("无此用户");
        if (!administrator.getPassword().equals(String.valueOf(token.getPassword())))
            throw new UnknownAccountException("密码错误");
        System.out.println("AdminRealm:" + getName());
        //盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
       // ByteSource credentialsSalt = ByteSource.Util.bytes(administrator.getAdid());
        return new SimpleAuthenticationInfo(
                administrator.getAdid(),
                administrator.getPassword(),
               // credentialsSalt,
                getName()
        );
    }
}
