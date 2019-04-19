package com.example.testonline.config;

import com.example.testonline.handle.TosExceptionHandler;
import com.example.testonline.shiro.CustomizedModularRealmAuthenticator;
import com.example.testonline.shiro.LoginType;
import com.example.testonline.shiro.realm.AdminRealm;
import com.example.testonline.shiro.realm.StudentRealm;
import com.example.testonline.shiro.realm.TeatherRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 *  这里使用Config配置，下次用xml、yml
 */
@Configuration
public class ShiroConfig {

    /**
     * 拦截器
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        System.out.println("ShiroConfig:shiroFilter");
        ShiroFilterFactoryBean shiroFilterFactoryBean =
                new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/");


        //拦截
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/css/**", "anon");
       // filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/templates/login/**","anon");
       // filterChainDefinitionMap.put("/plugins/**", "anon");
       // filterChainDefinitionMap.put("/custom-js/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");
       // filterChainDefinitionMap.put("/leftmenu", "anon");
       // filterChainDefinitionMap.put("/checkLogin", "anon");
       // filterChainDefinitionMap.put("/ui/articles", "anon");

        // 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");

        //我也没配置角色的权限。。。

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 加密方式
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        System.out.println("ShiroConfig:Matcher");
        HashedCredentialsMatcher hashedCredentialsMatcher =
                new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5"); // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);   //散列次数
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);// 表示是否存储散列后的密码为16进制，需要和生成密码时的一样，默认是base64
        return hashedCredentialsMatcher;
    }

    /**
     * 没弄缓冲区
     * @return
     */

    @Bean
    public AdminRealm adminRealm() {
        System.out.println("ShiroConfig:adminRealm");
        AdminRealm adminRealm = new AdminRealm();
      //  adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        adminRealm.setName(LoginType.ADMIN.getType());
        return adminRealm;
    }

    @Bean
    public TeatherRealm teatherRealm() {
        System.out.println("ShiroConfig:teacherRealm");
        TeatherRealm teatherRealm = new TeatherRealm();
      //  teatherRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        teatherRealm.setName(LoginType.TEACHER.getType());
        return teatherRealm;
    }

    @Bean
    public StudentRealm studentRealm() {
        System.out.println("ShiroConfig:studentRealm");
        StudentRealm studentRealm = new StudentRealm();
       // studentRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        studentRealm.setName(LoginType.STUDENT.getType());
        return  studentRealm;
    }

    /**
     * 多Realm
     * @return
     */

    @Bean
    public Collection<Realm> realms() {
        System.out.println("ShiroConfig:realms");
        Collection<Realm> realms = new ArrayList<>();
        ((ArrayList<Realm>) realms).add(studentRealm());
        ((ArrayList<Realm>) realms).add(teatherRealm());
        ((ArrayList<Realm>) realms).add(adminRealm());
        return realms;
    }

    @Bean
    AtLeastOneSuccessfulStrategy atLeastOneSuccessfulStrategy() {
        return new AtLeastOneSuccessfulStrategy();
    }

    @Bean
    CustomizedModularRealmAuthenticator authenticator() {
        System.out.println("ShiroConfig:authenticator");
        CustomizedModularRealmAuthenticator authenticator = new CustomizedModularRealmAuthenticator();
        authenticator.setAuthenticationStrategy(atLeastOneSuccessfulStrategy());
        return authenticator;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        System.out.println("ShiroConfig:sessionManager");
        DefaultWebSessionManager defaultWebSessionManager =
                new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(-1001L);
        return defaultWebSessionManager;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        System.out.println("ShiroConfig:securityManager");
        DefaultWebSecurityManager defaultWebSecurityManager =
                new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setAuthenticator(authenticator());
        defaultWebSecurityManager.setRealms(realms());
        return defaultWebSecurityManager;
    }
    /**
     * Shiro生命周期处理器 ---可以自定的来调用配置在 Spring IOC 容器中 shiro bean 的生命周期方法.
     *
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启shiro注解 ----启用 IOC 容器中使用 shiro 的注解. 但必须在配置了 LifecycleBeanPostProcessor
     * 之后才可以使用
     *
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    /**
     * 注册全局异常处理
     * @return
     */
    @Bean(name = "exceptionHandler")
    public TosExceptionHandler handlerExceptionResolver() {
        return new TosExceptionHandler();
    }

}


