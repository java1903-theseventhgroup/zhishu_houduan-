package com.qf.service;

import com.qf.domain.Administrator;

import java.util.List;

public interface AdministratorService {
    List<Administrator> selectAll(Integer page,Integer rows);
   public int selectRows(int rows);
   public Administrator checkName(Administrator administrator);
   public  int updateAdmin(Administrator administrator);
   public int addAdmin(Administrator administrator);
   public int deleteAdmin(int id);
   public Administrator selectOne(int id);
   public Administrator findAdminByName(String name);
}
