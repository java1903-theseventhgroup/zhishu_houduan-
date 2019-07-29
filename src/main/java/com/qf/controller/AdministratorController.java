package com.qf.controller;

import com.qf.domain.Administrator;
import com.qf.service.AdministratorService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/adminLogin", method = RequestMethod.GET)
    @ApiOperation(value = "管理员所有信息")
    public String adminLogin(@RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false, defaultValue = "5") int rows, Model model) {
        int maxpage = administratorService.selectRows(rows);

        if (page < 1) {
            page = 1;
        }
        if (page > maxpage) {
            page = maxpage;
        }
        List<Administrator> list = administratorService.selectAll(page, rows);
        model.addAttribute("List", list);
        model.addAttribute("maxpage", maxpage);
        model.addAttribute("page", page);
        return list != null ? "role" : "error";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {

        return "login";
    }

    @RequestMapping(value = "checkNames", method = RequestMethod.GET)
    public String checkNames() {
        return "main";
    }

    @RequestMapping(value = "checkName", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "管理员登录")
    public String checkName(Administrator administrator, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(administrator.getName(), administrator.getPass());
//			用户登录
        subject.login(token);
        if (subject.isAuthenticated()) {
            HttpSession session = request.getSession();
            session.setAttribute("checkName", administrator);
            return "1";
        } else {
            return "0";
        }

    }


    @RequestMapping("/{path1}" + "view")
    public String show1(@PathVariable("path1") String path1) {
        return path1;
    }

    @RequestMapping("/{path1}/{path2}" + "view")
    public String show1(@PathVariable("path1") String path1, @PathVariable("path2") String path2) {
        return path1 + "/" + path2;
    }

    // 注销登录
    @RequestMapping("loginout")
    public String loginout(HttpSession session , Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    // 从session里面取出当前用户的名字
    @RequestMapping("loginnames")
    @ResponseBody
    public String loginnames(HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("checkName");
        String name = administrator.getName();
        return name;

    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新增管理员")
    public String addAdmin(Administrator administrator) {
        int i = administratorService.addAdmin(administrator);
        return i > 0 ? "1" : "0";
    }

    @RequestMapping(value = "updateAdmin", method = RequestMethod.POST)
    @ResponseBody
    public String updateAdmin(Administrator administrator) {
        int i = administratorService.updateAdmin(administrator);
        return i>0?"1":"0";
    }

    @RequestMapping(value = "deleteAdmin")
    public String deleteAdmin(int id) {
        int i = administratorService.deleteAdmin(id);
        return "redirect:adminLogin";
    }
    @RequestMapping(value = "selectOne")
    public String selectOne(int id,Model model){
        Administrator list = administratorService.selectOne(id);
        model.addAttribute("list",list);
        return "updateadmin";
    }
}
