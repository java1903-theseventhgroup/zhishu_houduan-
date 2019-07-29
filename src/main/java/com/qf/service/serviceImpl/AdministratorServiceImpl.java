package com.qf.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qf.dao.AdministratorDao;
import com.qf.domain.Administrator;
import com.qf.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;
    @Override
    public List<Administrator> selectAll(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<Administrator> list = administratorDao.selectAll(page,rows);
        return list;
    }

    @Override
    public int selectRows(int rows) {
        int i = administratorDao.selectRows();
        return i%rows>0?i/rows+1:i/rows;
    }

    @Override
    public Administrator checkName(Administrator administrator) {
        Administrator checkName = administratorDao.checkName(administrator);
        return checkName;
    }

    @Override
    public int updateAdmin(Administrator administrator) {
        int i = administratorDao.updateAdmin(administrator);
        return i;
    }

    @Override
    public int addAdmin(Administrator administrator) {
        int i = administratorDao.addAdmin(administrator);
        return i;
    }

    @Override
    public int deleteAdmin(int id) {
        return administratorDao.deleteAdmin(id);
    }

    @Override
    public Administrator selectOne(int id) {
        Administrator administrator = administratorDao.selectOne(id);
        return administrator;
    }

    @Override
    public Administrator findAdminByName(String name) {
        return administratorDao.findAdminByName(name);
    }
}
