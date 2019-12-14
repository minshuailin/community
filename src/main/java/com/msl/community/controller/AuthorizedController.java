package com.msl.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Author:msl
 * @Description: Date:Created in 11:30 2019/12/14
 *
 * 授权github登录后，要跳转的页面
 */
@Controller
public class AuthorizedController {

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name = "state") String state){
        return "index";
    }
}
