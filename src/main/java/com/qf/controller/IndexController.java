package com.qf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    /**
     * 首页，localhost:8080直接返回index页面
     */
    @RequestMapping(value = "/")
    public String index(){

        return "index";
    }

    @RequestMapping(value = "one")
    public String one(){

        return "one";
    }
    @RequestMapping(value = "login")
    public String login(){

        return "login";
    }
    @RequestMapping(value = "book")
    public String book(){

        return "book";
    }
}