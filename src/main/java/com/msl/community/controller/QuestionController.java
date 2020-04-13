package com.msl.community.controller;

import com.msl.community.dto.CommentDTO;
import com.msl.community.dto.QuesetionDTO;
import com.msl.community.enums.CommentTypeEnum;
import com.msl.community.service.CommentService;
import com.msl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id, Model model){
        QuesetionDTO quesetionDTO =questionService.getById(id);
        //获得该问题的评论列表 问题的id拿到
        List<CommentDTO> comments =commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("question",quesetionDTO);
        model.addAttribute("comments",comments);

        return "question";
    }
}
