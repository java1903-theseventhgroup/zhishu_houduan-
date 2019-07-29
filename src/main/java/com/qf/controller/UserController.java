package com.qf.controller;

import com.qf.domain.User;
import com.qf.response.QueryResponseResult;
import com.qf.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */

    //这个借口用ajax请求后返回到验证码后面，显示邮件已发送成功
    @ApiOperation("这个借口用ajax请求后返回到验证码后面，显示邮件已发送成功。验证必须输入用户名，密码，邮箱")
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public String register(User user){
       /* if(user.getPassword()==null){
            return "error";
        }
        if (user.getUsername()==null){
            return "error";
        }
        if (user.getUseremail()==null){
            return "error";
        }*/
        user.setStatus(0);
        int num=(int)(Math.random()*8999)+1000;
        //double num=Math.random()*9000+1000;
        System.out.println(num);
        String code=Integer.toString(num);
        //String code = UUIDUtils.getUUID()+ UUIDUtils.getUUID();
        user.setCode(code);
        userService.register(user);
        return "邮件发送成功";
    }

    /**
     *校验邮箱中的code激活账户
     * 首先根据激活码code查询用户，之后再把状态修改为"1"
     */
    @RequestMapping(value = "/checkCode",method = RequestMethod.POST)
    @ApiOperation("校验邮箱中的code激活账户\n" +
            "     首先根据激活码code查询用户，之后再把状态修改为\"1\"")
    public QueryResponseResult checkCode(String code){
        QueryResponseResult queryResponseResult = userService.checkCode(code);
        System.out.println(queryResponseResult);
        //如果用户不等于null，把用户状态修改status=1
         return queryResponseResult;

    }

    /**
     * 通过用户名和密码或邮箱号和密码登录
     */
    @ApiOperation("通过用户名和密码或邮箱号和密码登录,登陆成功返回welcom,登录失败返回login")
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public QueryResponseResult login(User user, HttpSession session) {

        QueryResponseResult queryResponseResult = userService.loginUser(user);
        System.out.println(queryResponseResult);
        session.setAttribute("admin" ,queryResponseResult);
        System.out.println(session.toString());


                return queryResponseResult;


        }
       /* if (u.getPassword()!=null) {
        return "error";*/

    //查询用户名是否被占用,用户名存在返回1，用户名不存在返回0；
    @RequestMapping(value = "/findUser",method = RequestMethod.GET)
    @ApiOperation( "查询用户名是否被占用,用户名存在返回1，用户名不存在返回0；")
    public QueryResponseResult findUser(String username){
        QueryResponseResult count = userService.findUser(username);
        System.out.println(count);
        return count;
    }

    @RequestMapping(value = "/findUserMoney",method = RequestMethod.POST)
    @ApiOperation( "根据用户ID查询用户的余额")
    public QueryResponseResult findUserMoney(int id){
        QueryResponseResult userMoney = userService.findUserMoney(id);
        return userMoney;
    }
    @RequestMapping(value = "/findUserMoneys",method = RequestMethod.POST)
    @ApiOperation("根据前端的ID和钱数，给当前用户充值")
    public QueryResponseResult findUserMoneys(User user){
        QueryResponseResult userMoneys = userService.findUserMoneys(user);
        Integer id = user.getId();
        QueryResponseResult userMoney = userService.findUserMoney(id);
        return userMoney;
    }

    //后台查询所有用户
    @RequestMapping(value = "/findAllUser")
    @ApiOperation("后台查询所有用户信息")
    public String findAllUser(Model model, @RequestParam(required = false,defaultValue = "1")Integer page,
                              @RequestParam(required = false,defaultValue = "5")Integer rows
                              ){
        int maxpage=userService.selectRows(rows);
        if (page<1){page=1;}
        if(page>maxpage){page=maxpage;}
        List<User> allUser = userService.findAllUser(page,rows);
        model.addAttribute("maxpage",maxpage);
        model.addAttribute("page",page);
        model.addAttribute("userList",allUser);
        return "user";
    }
    //根据前端的ID和钱数，给当前用户减钱（支付）
    @RequestMapping(value = "/findUserJian",method = RequestMethod.POST)
    @ApiOperation("根据前端的ID和钱数，给当前用户减钱（支付）")
    public QueryResponseResult findUserJian(User user){
        QueryResponseResult userJian = userService.findUserJian(user);
        Integer id = user.getId();
        QueryResponseResult userMoney = userService.findUserMoney(id);
        return userMoney;
    }
    @ApiOperation("根据用户名和邮箱，查询用户后，发送邮件，修改密码")
    @RequestMapping(value ="/findUserByName",method = RequestMethod.POST)
    public String findUserByName(User user){
        int num=(int)(Math.random()*8999)+1000;
        String code=Integer.toString(num);
        user.setCode(code);
        int userByName = userService.findUserByName(user);
        String username = user.getUsername();
        userService.addCode(user);
        if (userByName>0){
        return "邮件发送成功";}
        else return "用户名或邮箱错误";
    }

    @ApiOperation("根据code修改对应用户的密码,并通过username清空code")
    @RequestMapping(value = "/uptPassWord",method = RequestMethod.POST)
    public QueryResponseResult uptPassWord(User user){
        QueryResponseResult queryResponseResult = userService.uptPassWord(user);
        return queryResponseResult;
    }

    //根据ID查询用户信息（后台接口）
    @ApiOperation("根据ID查询用户信息（后台接口）")
    @RequestMapping(value = "/selUser")
    public String selUser(int id,Model model){
        User user = userService.selUser(id);
        model.addAttribute("u",user);
        return "updateuser";
    }

    //根据ID和status修改用户信息（后台接口）
    @ApiOperation("根据ID和status修改用户信息（后台接口）")
    @RequestMapping(value = "/updateUser")
    public String updateUser(User user){
        int i = userService.updateUser(user);
        return "redirect:/user/findAllUser";
    }
}