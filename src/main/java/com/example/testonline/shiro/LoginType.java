package com.example.testonline.shiro;

public enum  LoginType {
    STUDENT("student"),
    ADMIN("admin"),
    TEACHER("teacher"),
    ;
    private String type;

    LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
