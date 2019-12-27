package com.msl.community.controller;

import com.msl.community.mapper.QuestionMapper;
import com.msl.community.mapper.UserMapper;
import com.msl.community.model.Question;
import com.msl.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("description")String description,
            @RequestParam("tag")String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);


        //获取用户
        User user = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user !=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        if(title == null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description == null || description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        //接受表单提交过来的数据
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());

        questionMapper.create(question);
        return "redirect:/";
    }
}
