package com.msl.community.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Author:msl
 * @Description: Date:Created in 15:07 2019/12/13
 */
@Controller
public class IndexController {


    @GetMapping({"/index","/"})
    public String index(){
        return "index";
    }
}
