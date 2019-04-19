package com.example.testonline.service;

import com.example.testonline.pojo.Teacher;

public interface TeacherService {
    Teacher select(String id);

    int insert(Teacher teacher);

    int delete(String id);

    int update(Teacher teacher);

}
