package com.msl.community.controller;

import com.msl.community.dto.QuesetionDTO;
import com.msl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        QuesetionDTO quesetionDTO =questionService.getById(id);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("question",quesetionDTO);
        return "question";
    }
}
