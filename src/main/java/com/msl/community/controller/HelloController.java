package com.msl.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author:msl
 * @Description: Date:Created in 15:07 2019/12/13
 */
@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello(@RequestParam(name="name") String name, Model model){

        model.addAttribute("name",name);
        return "hello";
    }
}
