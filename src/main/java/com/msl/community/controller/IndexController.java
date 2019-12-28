package com.msl.community.controller;

import com.msl.community.dto.QuesetionDTO;
import com.msl.community.mapper.UserMapper;
import com.msl.community.model.User;
import com.msl.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Author:msl
 * @Description: Date:Created in 15:07 2019/12/13
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping({"/index","/"})
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }

                    break;
                }
            }
        }

        List<QuesetionDTO> questionList = questionService.list();
        model.addAttribute("questions",questionList);

        return "index";
    }
}
