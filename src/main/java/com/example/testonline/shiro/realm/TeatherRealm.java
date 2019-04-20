package com.example.testonline.shiro.realm;

import com.example.testonline.pojo.Teacher;
import com.example.testonline.service.TeacherService;
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

public class TeatherRealm extends AuthorizingRealm {

    @Resource
    private TeacherService teacherService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principalCollection.getRealmNames().contains("teacher")){
            info.addRole("teacher");
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CostomLoginToken token = (CostomLoginToken) authenticationToken;
        Teacher teacher = teacherService.select(token.getUsername());
        if (teacher == null) throw new UnknownAccountException("账号不存在");
        if (!teacher.getPassword().equals(String.valueOf(token.getPassword())))
            throw new UnknownAccountException("密码错误");
        return new SimpleAuthenticationInfo(
                teacher.getTid(),
                teacher.getPassword(),
                getName()
        );
    }
}
