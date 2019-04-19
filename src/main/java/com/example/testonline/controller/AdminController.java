package com.example.testonline.controller;

import com.example.testonline.pojo.Administrator;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import com.example.testonline.result.TosException;
import com.example.testonline.service.AdminService;
import com.example.testonline.shiro.CostomLoginToken;
import com.example.testonline.shiro.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping(value = "/templates")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping(value = "/login/AdminLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String,String> person) {
        Subject subject = SecurityUtils.getSubject();
       // subject.getSession().setTimeout(-1001L); //当timeout设置为负数时 表示永不过期
                                                // timeout中的时间单位为ms，但是Shiro会把这个时间转成:s，
                                                // 而且是会舍掉小数部分，这样我设置的是-1ms，转成s后就是0s，马上就过期了。
        if (!subject.isAuthenticated()){
            CostomLoginToken token = new CostomLoginToken(person.get("id"),person.get("password"), LoginType.ADMIN.getType());
            try{
                subject.login(token);
            }catch (UnknownAccountException e1){
              //  e1.printStackTrace();
                throw new TosException(CodeEnum.AuthenticationException);
            }catch (AuthenticationException e2){
              //  e2.printStackTrace();
                throw new TosException(CodeEnum.AuthenticationException);
            }
        }
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/selectAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Result<Administrator> selectAdmin(@RequestBody Map<String, String> person) {
        String id = person.get("id");
        Administrator administrator = adminService.select(id);
        if (administrator == null) throw new TosException(CodeEnum.NoUserException);
        return ResultUtil.sussess(administrator);

    }

    @RequestMapping(value = "/adminUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Result updateAdmin(@RequestBody Administrator administrator) {
        adminService.update(administrator);
        return ResultUtil.sussess();
    }
}
