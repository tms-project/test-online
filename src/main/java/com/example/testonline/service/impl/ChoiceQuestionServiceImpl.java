package com.example.testonline.service.impl;

import com.example.testonline.mapper.ChoicequsetionMapper;
import com.example.testonline.pojo.Choicequsetion;
import com.example.testonline.result.CodeEnum;
import com.example.testonline.service.ChoiceQuestionService;
import com.example.testonline.result.TosException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChoiceQuestionServiceImpl implements ChoiceQuestionService {

    @Resource
    private ChoicequsetionMapper choicequsetionMapper;

    @Override
    public List<Choicequsetion> select() {
        return choicequsetionMapper.getListAll();
    }

    @Override
    public List<Choicequsetion> selectAt(int number) {
        List<Choicequsetion> choicequsetions = choicequsetionMapper.getListAll();
        List<Choicequsetion> choicequsetionList = new ArrayList<>(number+1);
        int size = choicequsetions.size();
        if (size < number) throw new TosException(CodeEnum.NoEnough);
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random(47);
        int cc = 0;
        while (true) {
            int n = random.nextInt(number);
            if (!set.contains(n)) {
                set.add(n);
                choicequsetionList.add(choicequsetions.get(n));
                cc++;
            }
            if (cc == number) break;
        }
        return choicequsetionList;
    }

    @Override
    public int insert(Choicequsetion choicequsetion) {
        int count = choicequsetionMapper.insert(choicequsetion);
        return count;
    }

    @Override
    public int update(Choicequsetion choicequsetion) {
        int count = choicequsetionMapper.update(choicequsetion);
       // if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }

    @Override
    public int delete(int id) {
        int count = choicequsetionMapper.delete(id);
        if (count == 0) throw new TosException(CodeEnum.NoUserException);
        return count;
    }
}
