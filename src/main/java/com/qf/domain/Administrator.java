package com.qf.domain;

import lombok.Data;

@Data
public class Administrator {
    private Integer id;   //管理员表ID
    private String name;  //管理员登录帐号
    private String pass;  //密码
    private String positions;   //职位
}
