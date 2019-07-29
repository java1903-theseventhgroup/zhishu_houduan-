package com.qf.domain;

import lombok.Data;

import java.io.Serializable;

@Data
    public class User implements Serializable {
        private Integer id;
        private String username;
        private String password;
        private String useremail;
        /**
         * 状态：0代表未激活，1代表激活
         */
        private Integer status;
        /**
         * 利用UUID生成一段数字，发动到用户邮箱，当用户点击链接时
         * 在做一个校验如果用户传来的code跟我们发生的code一致，更改状态为“1”来激活用户
         */
        private String  code;
        private Double money;


    }

