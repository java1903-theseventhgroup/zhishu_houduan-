package com.qf.dao;

import com.qf.domain.ClassIfications;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClassIficationsDao {
    List<ClassIfications> findSujectsByName(String subject);
}
