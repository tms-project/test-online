package com.example.testonline.service.impl;

import com.example.testonline.mapper.StudentMapper;
import com.example.testonline.pojo.Student;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.service.StudentService;
import com.example.testonline.result.TosException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentSeviveImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student select(String id) {
        Student student = studentMapper.getOneById(id);
        if (student == null) throw new TosException(CodeEnum.NoUserException);
        return student;
    }

    @Override
    public int insert(Student student) {
        Student s = studentMapper.getOneById(student.getSid());
        if (s != null) {
            throw new TosException(CodeEnum.UserExistException);
        }
        return studentMapper.insert(student);
    }

    @Override
    public int delete(String id) {
        int count = studentMapper.delete(id);
        if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }

    @Override
    public int update(Student student) {
        int count = studentMapper.update(student);
      //  if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }
}
