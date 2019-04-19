package com.example.testonline.service;

import com.example.testonline.pojo.Administrator;

public interface AdminService {

    int update(Administrator administrator);
    Administrator select(String id);

}
