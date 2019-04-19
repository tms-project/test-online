package com.example.testonline.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CostomLoginToken extends UsernamePasswordToken {

    private String loginType;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public CostomLoginToken() {
        super();
    }

    public CostomLoginToken(String username, String password, String loginType) {
        super(username, password);
        this.loginType = loginType;
    }

}
