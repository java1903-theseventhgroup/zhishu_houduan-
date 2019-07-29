package com.qf.service.serviceImpl;

import com.qf.dao.UserDao;
import com.qf.domain.User;
import com.qf.response.CommonCode;
import com.qf.response.QueryResponseResult;
import com.qf.response.QueryResult;
import com.qf.service.MailService;
import com.qf.service.UserService;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import java.util.List;

@Service
@Transactional
public class UserServiceimpl implements UserService{

    @Autowired
    private UserDao userDao;

    /**
     * 注入邮件接口
     */
    @Autowired
    private MailService mailService;

    /**
     * 用户注册，同时发送一封激活邮件
     *
     * @param user
     */
    @Override
    public void register(User user) {
        userDao.register(user);
        System.out.println(user);
        //获取激活码
        String code = user.getCode();
        System.out.println("code:" + code);
        //主题
        String subject = "来自千峰项目组的激活邮件";
        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        String context = "<a>您的验证码是:" + code + "</a>";
        //String context ="<a href=\"http://localhost:8080/user/checkCode?code="+code+"\">您的验证码是:"+code+"</a>";
        //发送激活邮件
        mailService.sendHtmlMail(user.getUseremail(), subject, context);
    }

    /**
     * 根据激活码code进行查询用户，之后再进行修改状态
     *
     * @param code
     * @return
     */
    @Override
    public QueryResponseResult checkCode(String code) {

        User user = userDao.checkCode(code);
        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setUser(user);
        if (user != null) {
            user.setStatus(1);
            //queryResponseResult.user.setStatus(1);
            //把code验证码清空，已经不需要了
            user.setCode("");
            System.out.println(user);
            userDao.updateUserStatus(user);
            return new QueryResponseResult(CommonCode.FINUSER, queryResult);
        } else {
            return new QueryResponseResult(CommonCode.FINNUSER, queryResult);
        }
    }

    /**
     * 激活账户，修改用户状态
     *
     * @param user
     */
    @Override
    public void updateUserStatus(User user) {
        userDao.updateUserStatus(user);
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public QueryResponseResult loginUser(User user) {
        User user1 = userDao.loginUser(user);
        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setUser(user1);

        if (user1 != null) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
        }
        return new QueryResponseResult<>(CommonCode.FAIL, queryResult);
    }

    @Override
    //查询用户名是否被占用
    public QueryResponseResult findUser(String username) {

        int count = userDao.findUser(username);
        QueryResult<Integer> queryResult = new QueryResult<>();
        queryResult.setAnInt(count);
        if (count > 0) {
            return new QueryResponseResult<>(CommonCode.FINDID, queryResult);
        } else {
            return new QueryResponseResult<>(CommonCode.FINDNID, queryResult);
        }
    }

    @Override
    //根据用户ID查询用户的余额
    public QueryResponseResult findUserMoney(int id) {
        Double userMoney = userDao.findUserMoney(id);
        QueryResult<Double> queryResult = new QueryResult<>();
        queryResult.setADouble(userMoney);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @Override
    //根据前端的ID和钱数，给当前用户充值
    public QueryResponseResult findUserMoneys(User user) {
        int userMoneys = userDao.findUserMoneys(user);
        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setAnInt(userMoneys);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    @Override
    //查询总行数
    public int selectRows(Integer rows) {
        int i = userDao.selectRows();
        return i%rows>0?i/rows+1:i/rows;
    }

    @Override
    //后台查询所有用户
    public List<User> findAllUser(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        return userDao.findAllUser(page,rows);
    }

    @Override
    //根据前端的ID和钱数，给当前用户减钱（支付）
    public QueryResponseResult findUserJian(User user) {
        int userJian = userDao.findUserJian(user);
        QueryResult<User> queryResult = new QueryResult<>();
        queryResult.setAnInt(userJian);
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }

    @Override
    //根据用户名和邮箱，查询用户后，发送邮件，修改密码
    public int findUserByName(User user) {
        int userByName = userDao.findUserByName(user);
            String code = user.getCode();
            System.out.println("code:" + code);
            String subject = "来自千峰项目组的激活邮件";
            //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
            //上面的激活码发送到用户注册邮箱
            String context = "<a>您的验证码是:" + code + "</a>";
            //String context ="<a href=\"http://localhost:8080/user/checkCode?code="+code+"\">您的验证码是:"+code+"</a>";
            //发送激活邮件
            mailService.sendHtmlMail(user.getUseremail(), subject, context);
            return userByName;
    }

    @Override
    //根据username查到code并且修改
    public int addCode(User user) {
        int i = userDao.addCode(user);
        return i;
    }

    @Override
    //根据code修改对应用户的密码,并通过username清空code
    public QueryResponseResult uptPassWord(User user) {
        String username = user.getUsername();
        int i = userDao.uptPassWord(user);
        user.setCode("");
        userDao.addCode(user);
        QueryResult<Integer> queryResult = new QueryResult<>();
        queryResult.setAnInt(i);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    @Override
    //根据ID查询用户信息（后台接口）
    public User selUser(int id) {
       return userDao.selUser(id);
    }

    @Override
    //根据ID和status修改用户信息（后台接口）
    public int updateUser(User user) {
       return userDao.updateUser(user);
    }
}