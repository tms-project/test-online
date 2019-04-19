package com.example.testonline.service;

import com.example.testonline.pojo.Student;

public interface StudentService {

    Student select(String id);

    int insert(Student student);

    int delete(String id);

    int update(Student student);

}
