package com.qf.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.qf.dao.BookDao;
import com.qf.dao.ListenDao;
import com.qf.domain.Book;
import com.qf.response.CommonCode;
import com.qf.response.QueryResponseResult;
import com.qf.response.QueryResult;
import com.qf.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;



    @Override
    public QueryResponseResult findAll() {
        List<Book> all = bookDao.findAll();
        QueryResult<Book> bookQueryResult=new QueryResult<>();
        bookQueryResult.setList(all);
        return new QueryResponseResult<>(CommonCode.SUCCESS,bookQueryResult);
    }

    @Override
    public QueryResponseResult findType(String bookType) {
        List<Book> type0 = bookDao.findType(bookType);
        QueryResult<Book> bookQueryResult=new QueryResult<>();
        bookQueryResult.setList(type0);
        return new QueryResponseResult<>(CommonCode.SUCCESS,bookQueryResult);
    }


    @Override
    public QueryResponseResult findS2(String classification) {
        List<Book> s2 = bookDao.findS2(classification);
        QueryResult<Book> queryResult=new QueryResult<>();
            queryResult.setList(s2);
            return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    @Override
    public QueryResponseResult findSujectsByName(String subject) {
        List<Book> sujectsByName = bookDao.findSujectsByName(subject);
        QueryResult<Book> queryResult=new QueryResult<>();
        queryResult.setList(sujectsByName);
        return new QueryResponseResult<>(CommonCode.SUCCESS,queryResult);
    }

    /*后端接口*/
    @Override
    public List<Book> findAllBook(Integer page,Integer rows) {
        PageHelper.startPage(page,rows);
        List<Book> allBook = bookDao.findAllBook(page,rows);


        return allBook;
    }

    @Override
    public int addBook(Book book) {
        int i = bookDao.addBook(book);
        return i;
    }

    @Override
    public int selectRows(Integer rows) {
        int i = bookDao.selectRows();
        return i%rows>0?i/rows+1:i/rows;
    }

    @Override
    public int deleteBook(Integer id) {
        int i = bookDao.deleteBook(id);
        return i;
    }

    @Override
    public Book findById(Integer id) {
        Book bookById = bookDao.findBookById(id);
        return bookById;
    }

    @Override
    public int updateBook(Book book) {

        return  bookDao.updateBook(book);
    }
}
