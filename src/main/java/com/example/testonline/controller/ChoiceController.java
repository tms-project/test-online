package com.example.testonline.controller;


import com.example.testonline.pojo.Choicequsetion;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.result.Result;
import com.example.testonline.result.ResultUtil;
import com.example.testonline.result.TosException;
import com.example.testonline.service.ChoiceQuestionService;
import com.example.testonline.service.ReadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.example.testonline.shiro.ShiroUtil.hasRole;

@Controller
@RequestMapping(value = "/templates")
public class ChoiceController {

    @Resource
    private ChoiceQuestionService choiceQuestionService;

    @Resource
    private ReadService readService;

    @RequestMapping(value = "/insertChoicequsetion",method = RequestMethod.POST)
    @ResponseBody
    public Result insertChoice(@RequestBody Choicequsetion choicequsetion) {
        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        choiceQuestionService.insert(choicequsetion);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/insertChoiceByExcel", method = RequestMethod.POST)
    @ResponseBody
    public Result insertChoiceByExcel(@RequestParam("file") MultipartFile file) {

        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        List<Object> datas = readService.excelReadService(file, Choicequsetion.class);
        Choicequsetion choicequsetion;
        for (Object data:
                datas) {
            choicequsetion = data instanceof Choicequsetion ? ((Choicequsetion) data) : null;
            if (choicequsetion != null) choiceQuestionService.insert((Choicequsetion)data);
        }
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/selectAllChoiceQuestion",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Choicequsetion>> selectAllChoice() {
        List<Choicequsetion> choicequsetions = choiceQuestionService.select();
        return ResultUtil.sussess(choicequsetions);
    }

    @RequestMapping(value = "/selectChoiceQuestionAt",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Choicequsetion>> selectChoiceAt(@RequestBody Map<String, String> count) {
        int c = Integer.valueOf(count.get("number"));
        List<Choicequsetion> choicequsetions = choiceQuestionService.selectAt(c);
        return ResultUtil.sussess(choicequsetions);
    }

    @RequestMapping(value = "/updateChoicequsetion",method = RequestMethod.POST)
    @ResponseBody
    public Result updateChoice(@RequestBody Choicequsetion choicequsetion) {
        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        choiceQuestionService.insert(choicequsetion);
        return ResultUtil.sussess();
    }

    @RequestMapping(value = "/deleteChoicequestion",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteChoice(@RequestBody Map<String, String> person) {
        if (!hasRole("admin","teacher")) throw new TosException(CodeEnum.JurisdictionException);
        String id = person.get("id");
        choiceQuestionService.delete(Integer.valueOf(id));
        return ResultUtil.sussess();
    }



}
