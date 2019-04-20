package com.example.testonline.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.concurrent.atomic.AtomicInteger;

public class ShiroUtil {

    public static boolean hasRole(String ... strings){
        Subject subject = SecurityUtils.getSubject();
        AtomicInteger count = new AtomicInteger();
        for (String role :
                strings) {
            if(subject.hasRole(role))
                count.getAndIncrement();
        }
        if (count.get() == 0) return false;
        else return true;
    }
}
