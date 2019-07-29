package com.qf.dao;


import com.qf.domain.Picture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureDao {
    Integer insertPicture(Picture picture);
}
