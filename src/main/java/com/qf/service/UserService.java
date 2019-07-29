package com.qf.service;

import com.qf.domain.User;
import com.qf.response.QueryResponseResult;

import java.util.List;

public interface UserService {
    /**
     * 用户注册,
     *
     * @param user
     */
    void register(User user);

    /**
     * 根据激活码code查询用户，之后再进行修改状态
     *
     * @param code
     * @return
     */
    QueryResponseResult checkCode(String code);

    /**
     * 激活账户，修改用户状态为“1”
     *
     * @param user
     */
    void updateUserStatus(User user);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    QueryResponseResult loginUser(User user);

    public QueryResponseResult findUser(String username);//查询用户名是否被占用

    public QueryResponseResult findUserMoney(int id);//根据用户ID查询用户的余额

    public QueryResponseResult findUserMoneys(User user);//根据前端的ID和钱数，给当前用户充值

    public List<User> findAllUser(Integer page, Integer rows);//后台查询所有用户
    public int selectRows(Integer rows);//查询总行数
    public QueryResponseResult findUserJian(User user);//根据前端的ID和钱数，给当前用户减钱（支付）
    public int findUserByName(User user);//根据用户名和邮箱，查询用户后，发送邮件，修改密码
    public int addCode(User user);//根据username查到code并且修改
    public QueryResponseResult uptPassWord(User user);//根据code修改对应用户的密码

    public User selUser(int id);//根据ID查询用户信息（后台接口）
    public int updateUser(User user);//根据ID和status修改用户信息（后台接口）
        }