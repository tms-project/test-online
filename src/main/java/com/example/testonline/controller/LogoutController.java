package com.example.testonline.controller;

import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//为什么@RestController不能放回页面，只能返回字符串

@Controller
@RequestMapping("/templates")
public class LogoutController {

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("退出成功");
        return ResultUtil.sussess();
    }

/*    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private AdminMapper adminMapper;

    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestBody Map<String, String> person) {
        String user = person.get("user");
        String id = person.get("id");
        String password = person.get("password");
        switch (user) {
            case "学生":
                Student student = studentMapper.getOneById(id);
                if (student != null) {
                    if(student.getPassword().equals(password)){
                        //这里怎么把用户登陆信息存起来
                        return "success";
                    } else
                        throw new TosException(CodeEnum.ParamException);  //抛出异常
                } else {
                    throw new TosException(CodeEnum.ParamException);  //抛出异常
                }
            case "教师":
                Teacher teacher = teacherMapper.getOneById(id);
                if (teacher != null) {
                    if(teacher.getPassword().equals(password)){
                        return "success";
                    }
                    else
                        throw new TosException(CodeEnum.ParamException);  //抛出异常
                } else {
                    throw new TosException(CodeEnum.ParamException);  //抛出异常
                }
            case "管理员":
                Administrator administrator = adminMapper.getOneById(id);
                if (administrator != null) {
                    if(administrator.getPassword().equals(password)) {
                        return "success";
                    }
                    else
                        throw new TosException(CodeEnum.ParamException);  //抛出异常
                } else {
                    throw new TosException(CodeEnum.ParamException);  //抛出异常
                }
            default:
                throw new TosException(CodeEnum.ParamException);
        }
    }*/

    @RequestMapping("/Test")
    public String Test(){
        return "Test";
    }


}
