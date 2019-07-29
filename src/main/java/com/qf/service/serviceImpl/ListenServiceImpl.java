package com.qf.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qf.dao.ListenDao;
import com.qf.domain.Book;
import com.qf.domain.Listen;
import com.qf.response.CommonCode;
import com.qf.response.QueryResponseResult;
import com.qf.response.QueryResult;
import com.qf.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenServiceImpl implements ListenService {
    @Autowired
    private ListenDao listenDao;


    @Override
    public QueryResponseResult findS21(String classification) {
        List<Listen> s21 = listenDao.findS21(classification);
        QueryResult<Listen> queryResult=new QueryResult<>();
            queryResult.setList(s21);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);

    }

    @Override
    public QueryResponseResult findSujectsByName(String subject) {
        List<Listen> sujectsByName = listenDao.findSujectsByName(subject);
        QueryResult<Listen> queryResult=new QueryResult<>();
        queryResult.setList(sujectsByName);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

/*    @Override
    public QueryResponseResult loadAll() {
        List<Listen> all=listenDao.loadAll();
        QueryResult<Listen> listenQueryResult = new QueryResult<>();
        listenQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,listenQueryResult);
    }*/

    @Override
    public QueryResponseResult selsctByListenType(String listenType) {
        List<Listen> all=listenDao.selsctByListenType(listenType);
        QueryResult<Listen> listenQueryResult = new QueryResult<>();
        listenQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,listenQueryResult);
    }

    @Override
    public QueryResponseResult selsctByListenAuthor(String listenAuthor) {
        List<Listen> all=listenDao.selsctByListenAuthor(listenAuthor);
        QueryResult<Listen> listenQueryResult = new QueryResult<>();
        listenQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,listenQueryResult);
    }

    @Override
//查询总行数
    public int selectRows(Integer rows) {
        int i = listenDao.selectRows();
        return i%rows>0?i/rows+1:i/rows;
    }

    @Override
//后台查询所有用户
    public List<Listen> loadAll(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        return listenDao.loadAll(page,rows);
    }

    @Override
    public boolean addListen(Listen listen) {
        Integer integer = listenDao.addListen(listen);
        return integer>0?true:false;
    }

    @Override
    public boolean updateListen(Listen listen) {
        Integer integer = listenDao.updateListen(listen);
        return integer>0?true:false;
    }

    @Override
    public boolean deleteListen(Integer listenId) {
        Integer integer = listenDao.deleteListen(listenId);
        return integer>0?true:false;
    }

    @Override
    public Listen findListenById(Integer listenId) {
        Listen listenById = listenDao.findListenById(listenId);
        return listenById;
    }

    @Override
    public boolean uploadListenImgUrl(Listen listen) {
        Integer integer = listenDao.uploadListenImgUrl(listen);
        return integer>0?true:false;
    }

    @Override
    public boolean uploadListenAudio(Listen listen) {
        Integer integer = listenDao.uploadListenAudio(listen);
        return integer>0?true:false;
    }
}
