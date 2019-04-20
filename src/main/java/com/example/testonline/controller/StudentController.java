package com.example.testonline.controller;


import com.example.testonline.pojo.Student;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import com.example.testonline.result.TosException;
import com.example.testonline.service.StudentService;
import com.example.testonline.shiro.CostomLoginToken;
import com.example.testonline.shiro.LoginType;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

import static com.example.testonline.shiro.ShiroUtil.hasRole;

@Controller
@RequestMapping(value = "/templates")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/login/StudentLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String,String> person) {
        Subject subject = SecurityUtils.getSubject();
        // subject.getSession().setTimeout(-1001L); //当timeout设置为负数时 表示永不过期
        // timeout中的时间单位为ms，但是Shiro会把这个时间转成:s，
        // 而且是会舍掉小数部分，这样我设置的是-1ms，转成s后就是0s，马上就过期了。
        if (!subject.isAuthenticated()){
            CostomLoginToken token = new CostomLoginToken(person.get("id"),person.get("password"), LoginType.STUDENT.getType());
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
        System.out.println("登陆成功");
        System.out.println("Student:Roles:student:admin:teacher:"+subject.hasRole("student")+subject.hasRole("admin")+subject.hasRole("teacher"));
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/insertStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result insertStu(@RequestBody Student student) {
        if (!hasRole("admin","student")) throw new TosException(CodeEnum.JurisdictionException);
        studentService.insert(student);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/selectStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result<Student> selectStu(@RequestBody Map<String, String> person) {
        String id = person.get("id");
        Student student = studentService.select(id);
        return ResultUtil.sussess(student);
    }

    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteStu(@RequestBody Map<String, String> person) {
        if (!hasRole("admin")) throw new TosException(CodeEnum.JurisdictionException);
        String id = person.get("id");
        studentService.delete(id);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    @ResponseBody
    public Result updateStu(@RequestBody Student student) {
        if (!hasRole("admin","student")) throw new TosException(CodeEnum.JurisdictionException);
        studentService.update(student);
        return ResultUtil.sussess();
    }
}
