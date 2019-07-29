package com.qf.dao;

import com.qf.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
        /**
         * 用户注册，注册的时候默认状态为0：未激活，并且调用邮件服务发送激活码到邮箱
         * @param user
         */
        void register(User user);

        /**
         * 点击邮箱中的激活码进行激活，根据激活码查询用户，之后再进行修改用户状态为1进行激活
         * @param code
         * @return
         */
        User checkCode(String code);

        /**
         * 激活账户，修改用户状态为“1”进行激活
         * @param user
         */
        void updateUserStatus(User user);

        /**
         * 登录，根据用户状态为“1”来查询
         * @param user
         * @return
         */
        User loginUser(User user);

        public int findUser(String username);//查询用户名是否被占用

        public Double findUserMoney(int id);//根据用户ID查询用户的余额
        public int findUserMoneys(User user);//根据前端的ID和钱数，给当前用户充值
        public List<User> findAllUser(Integer page, Integer rows);//后台查询所有用户(后台管理)
        public int selectRows();//查询总行数；
        public int findUserJian(User user);//根据前端的ID和钱数，给当前用户减钱（支付）
        public int findUserByName(User user);//根据用户名和邮箱，查询用户后，发送邮件，修改密码
        public int addCode(User user);//根据username查到code并且修改
        public int uptPassWord(User user);//根据code修改对应用户的密码
        public User selUser(int id);//根据ID查询用户信息（后台接口）
        public int updateUser(User user);//修改用户信息（后台接口）
    }

