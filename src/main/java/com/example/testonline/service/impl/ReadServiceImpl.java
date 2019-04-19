package com.example.testonline.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.service.ReadService;
import com.example.testonline.result.TosException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class ReadServiceImpl implements ReadService {

    @Override
    public List<Object> excelReadService(MultipartFile file, Class<? extends BaseRowModel> clazz) {
        if (file.isEmpty()) throw new TosException(CodeEnum.ParamException);
        if(!file.getOriginalFilename().endsWith(".xlsx")) throw new TosException(CodeEnum.ParamException);
       // System.out.println(file.getName()+":"+file.getOriginalFilename()+":"+file.getContentType());
        InputStream in;
        try {
            in = file.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TosException(CodeEnum.UnknownException);
        }
        List<Object> datas = EasyExcelFactory.read(in, new Sheet(1,1, clazz));
        try {
            in.close();
        } catch (Exception e){
            e.printStackTrace();
            throw new TosException(CodeEnum.UnknownException);
        }
        return datas;
    }
}
