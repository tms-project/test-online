package com.example.testonline.controller;

import com.example.testonline.pojo.Teacher;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import com.example.testonline.result.TosException;
import com.example.testonline.service.TeacherService;
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

import static com.example.testonline.shiro.ShiroUtil.hasRole;

@Controller
@RequestMapping(value = "/templates")
public class TeacherController  {

    @Resource
    private TeacherService teacherService;

    @RequestMapping(value = "/login/TeacherLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String,String> person) {
        Subject subject = SecurityUtils.getSubject();
        // subject.getSession().setTimeout(-1001L); //当timeout设置为负数时 表示永不过期
        // timeout中的时间单位为ms，但是Shiro会把这个时间转成:s，
        // 而且是会舍掉小数部分，这样我设置的是-1ms，转成s后就是0s，马上就过期了。
        if (!subject.isAuthenticated()){
            CostomLoginToken token = new CostomLoginToken(person.get("id"),person.get("password"), LoginType.TEACHER.getType());
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
        System.out.println("Student:Roles:student:admin:teacher:"+subject.hasRole("student")+subject.hasRole("admin")+subject.hasRole("teacher"));
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/insertTeacher",method = RequestMethod.POST)
    @ResponseBody
    public Result insertTea(@RequestBody Teacher teacher) {
        if (!hasRole("admin")) throw new TosException(CodeEnum.JurisdictionException);
        teacherService.insert(teacher);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/selectTeacher",method = RequestMethod.POST)
    @ResponseBody
    public Result<Teacher> selectTea(@RequestBody Map<String, String> person) {
        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        String id = person.get("id");
        Teacher teacher = teacherService.select(id);
        return  ResultUtil.sussess(teacher);
    }

    @RequestMapping(value = "/updateTeacher",method = RequestMethod.POST)
    @ResponseBody
    public Result updateTea(@RequestBody Teacher teacher) {
        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        teacherService.update(teacher);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/deleteTeacher",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteTea(@RequestBody Map<String, String> person) {
        if (!hasRole("admin")) throw new TosException(CodeEnum.JurisdictionException);
        String id = person.get("id");
        teacherService.delete(id);
        return ResultUtil.sussess();
    }

}
