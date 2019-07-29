package com.qf.dao;

import com.qf.domain.Administrator;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdministratorDao {
    public Administrator findAdminByName(String name);
    //查询总行数加分页
    public List<Administrator> selectAll(Integer page,Integer rows);
    //修改
    public int updateAdmin(Administrator admin);
    //查询总行数
    public int selectRows();
    //登录
    public Administrator checkName(Administrator administrator);
    //新增
    public int addAdmin(Administrator administrator);
    //删除
    public int deleteAdmin(int id);
    //根据ID查信息
    public Administrator selectOne(int id);
}
