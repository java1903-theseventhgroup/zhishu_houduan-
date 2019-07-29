package com.qf.dao;

import com.qf.domain.Listen;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ListenDao {
    List<Listen> findS21(String classification);
    List<Listen> findSujectsByName(String subject);
    List<Listen> loadAll(Integer page,Integer rows);//加载所有听书
    int selectRows();
    List<Listen> selsctByListenType(String listenType);
    List<Listen> selsctByListenAuthor(String listenAuthor);
    Integer addListen(Listen listen);
    Integer updateListen(Listen listen);
    Integer deleteListen(Integer listenId);
    Listen findListenById(Integer listenId);
    Integer uploadListenImgUrl(Listen listen);
    Integer uploadListenAudio(Listen listen);

}
