package com.example.testonline.service.impl;

import com.example.testonline.mapper.TeacherMapper;
import com.example.testonline.pojo.Teacher;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.service.TeacherService;
import com.example.testonline.result.TosException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher select(String id) {
        Teacher teacher = teacherMapper.getOneById(id);
        if (teacher == null) throw new TosException(CodeEnum.NoUserException);
        return teacher;
    }

    @Override
    public int insert(Teacher teacher) {
        Teacher s = teacherMapper.getOneById(teacher.getTid());
        if (s != null) {
            throw new TosException(CodeEnum.UserExistException);
        }
        return teacherMapper.insert(teacher);
    }

    @Override
    public int delete(String id) {
        int count = teacherMapper.delete(id);
        if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }

    @Override
    public int update(Teacher teacher) {
        int count = teacherMapper.update(teacher);
      //  if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }
}
