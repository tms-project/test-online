package com.example.testonline.service.impl;

import com.example.testonline.mapper.AdminMapper;
import com.example.testonline.pojo.Administrator;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.service.AdminService;
import com.example.testonline.result.TosException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Override
    public int update(Administrator administrator) {
        int count = adminMapper.update(administrator);
/*        if (count == 0){
            throw new TosException(CodeEnum.NoUserException);
        } else*/
            return count;
    }

    @Override
    public Administrator select(String id) {
        Administrator administrator = adminMapper.getOneById(id);
            return administrator;
    }
}
