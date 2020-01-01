package com.msl.community.controller;

import com.msl.community.dto.PaginationDTO;
import com.msl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:msl
 * @Description: Date:Created in 15:07 2019/12/13
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping({"/index","/"})
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page, //分页 需要传进来两个参数，每一页的页码 每一页的分页数
                        @RequestParam(name = "size",defaultValue = "5")Integer size){

        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}
