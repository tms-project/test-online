package com.example.testonline.service;

import com.example.testonline.pojo.Fillquestion;

import java.util.List;

public interface FillQuestionService {

    List<Fillquestion> select();

    List<Fillquestion> selectAt(int number);

    int insert(Fillquestion fillquestion);

    int update(Fillquestion fillquestion);

    int delete(int id);
}
