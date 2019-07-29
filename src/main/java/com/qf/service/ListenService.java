package com.qf.service;

import com.qf.domain.Listen;
import com.qf.response.QueryResponseResult;

import java.util.List;

public interface ListenService {
    QueryResponseResult findS21(String classification);
    QueryResponseResult findSujectsByName(String subject);
//    QueryResponseResult loadAll();//加载所有听书
    public List<Listen> loadAll(Integer page, Integer rows);//后台查询所有用户
    public int selectRows(Integer rows);
    QueryResponseResult selsctByListenType(String listenType);
    QueryResponseResult selsctByListenAuthor(String listenAuthor);
    boolean addListen(Listen listen);
    boolean updateListen(Listen listen);
    boolean deleteListen(Integer listenId);
    Listen findListenById(Integer listenId);
    boolean uploadListenImgUrl(Listen listen);
    boolean uploadListenAudio(Listen listen);
}
