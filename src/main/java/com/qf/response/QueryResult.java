package com.qf.response;

import com.qf.domain.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@ToString
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
    private User user;
    private int anInt;
    private Double aDouble;

}
