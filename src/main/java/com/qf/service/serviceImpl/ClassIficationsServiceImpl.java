package com.qf.service.serviceImpl;

import com.qf.dao.ClassIficationsDao;
import com.qf.domain.ClassIfications;
import com.qf.response.CommonCode;
import com.qf.response.QueryResponseResult;
import com.qf.response.QueryResult;
import com.qf.service.ClassIficationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassIficationsServiceImpl implements ClassIficationsService {
    @Autowired
    private ClassIficationsDao classIficationsDao;
    @Override
    public QueryResponseResult findSujectsByName(String subject) {
        List<ClassIfications> sujectsByName = classIficationsDao.findSujectsByName(subject);
        QueryResult<ClassIfications> queryResult=new QueryResult<>();
        queryResult.setList(sujectsByName);
    return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }
}
