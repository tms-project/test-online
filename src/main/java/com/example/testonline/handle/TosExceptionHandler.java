package com.example.testonline.handle;

import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import com.example.testonline.result.TosException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class TosExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handlerTosException(TosException e) {
        return ResultUtil.error(e.getCode(),e.getMsg());
    }

}
