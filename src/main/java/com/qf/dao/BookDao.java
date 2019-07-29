package com.qf.dao;

import com.qf.domain.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookDao {
    List<Book> findAll();
    List<Book> findType(String bookType);
    List<Book> findS2(String classification);
    List<Book> findSujectsByName(String subject);
    /*后端接口*/
    public List<Book> findAllBook(Integer page,Integer rows);
    public int selectRows();
    public int addBook(Book book);
    public int deleteBook(Integer id);
    public Book findBookById(Integer id);
    public int updateBook(Book book);
}
