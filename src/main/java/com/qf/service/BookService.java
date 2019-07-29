package com.qf.service;

import com.qf.domain.Book;
import com.qf.response.QueryResponseResult;

import java.util.List;

public interface BookService {
    QueryResponseResult findAll();
    QueryResponseResult findType(String bookType);
    QueryResponseResult findS2(String classification);
    QueryResponseResult findSujectsByName(String subject);


    /*后端接口*/
    public List<Book> findAllBook(Integer page,Integer rows);
    public int addBook(Book book);
    public int selectRows(Integer rows);
    public int deleteBook(Integer id);
    public Book findById(Integer id);
    public int updateBook(Book book);
}
