package com.example.testonline.shiro.realm;

import com.example.testonline.pojo.Student;
import com.example.testonline.service.StudentService;
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

public class StudentRealm extends AuthorizingRealm {

    @Resource
    private StudentService studentService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Set<String> roles = new HashSet<>(2);
        roles.add(LoginType.STUDENT.getType());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CostomLoginToken token = (CostomLoginToken) authenticationToken;
        Student student = studentService.select(token.getUsername());
        if (student == null) throw new UnknownAccountException("账号不存在");
        if (!student.getPassword().equals(String.valueOf(token.getPassword())))
            throw new UnknownAccountException("密码错误");
        return new SimpleAuthenticationInfo(
                student.getSid(),
                student.getPassword(),
                getName()
        );
    }

}
