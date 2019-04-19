package com.example.testonline.service;

import com.example.testonline.pojo.Choicequsetion;

import java.util.List;

public interface ChoiceQuestionService {

    List<Choicequsetion> select();

    List<Choicequsetion> selectAt(int number);

    int insert(Choicequsetion choicequsetion);

    int update(Choicequsetion choicequsetion);

    int delete(int id);

}
