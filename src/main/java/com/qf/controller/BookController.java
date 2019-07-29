package com.qf.controller;

import com.qf.domain.Book;
import com.qf.response.QueryResponseResult;
import com.qf.service.BookService;
import com.qf.service.CourseService;
import com.qf.service.ListenService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ListenService listenService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "findAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询所有的电子书")
    public QueryResponseResult findAll() {
        QueryResponseResult all = bookService.findAll();
        return all;
    }

    @RequestMapping(value = "findType", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询所有精选书单0/限时特价2")
    public QueryResponseResult findType(@RequestParam("books") String bookType) {
        QueryResponseResult type0 = bookService.findType(bookType);
        return type0;
    }

    @RequestMapping(value = "findS2", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询二级目录下的所有三级相关信息")
    public List<QueryResponseResult> findS2(@RequestBody String classification) {

        QueryResponseResult s2 = bookService.findS2(classification);
        QueryResponseResult s21 = listenService.findS21(classification);
        QueryResponseResult s22 = courseService.findS22(classification);
        List<QueryResponseResult> list = new ArrayList<>();
        list.add(s2);
        list.add(s21);
        list.add(s22);
        return list;

    }

    @RequestMapping(value = "findSujectsByName", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查询一级目录下的所有")
    public List<QueryResponseResult> findSujectsByName(@RequestBody String subject) {
        QueryResponseResult sujectsByName = bookService.findSujectsByName(subject);
        QueryResponseResult sujectsByName1 = courseService.findSujectsByName(subject);
        QueryResponseResult sujectsByName2 = listenService.findSujectsByName(subject);
        List<QueryResponseResult> list = new ArrayList<>();
        list.add(sujectsByName);
        list.add(sujectsByName1);
        list.add(sujectsByName2);
        return list;
    }


    @RequestMapping(value = "/findAllBook", method = RequestMethod.GET)
    @ApiOperation(value = "获得所有电子书", notes = "test: 仅1和2有正确返回")
    public String getAllBook(@RequestParam(required = false, defaultValue = "1") Integer page, @RequestParam(required = false, defaultValue = "5") Integer rows, Model model) {
        int maxpage = bookService.selectRows(rows);
        if (page < 1) {
            page = 1;
        }
        if (page > maxpage) {
            page = maxpage;
        }

        List<Book> allBook = bookService.findAllBook(page, rows);
        model.addAttribute("maxpage", maxpage);
        model.addAttribute("page", page);
        model.addAttribute("book", allBook);

        return "ebook";
    }

    @RequestMapping(value = "/addBook")
    public String addBook(Book book) {
        int i = bookService.addBook(book);
        return "redirect:findAllBook";
    }

    @RequestMapping(value = "/deleteBook")
    @ApiOperation(value = "根据id删除书", notes = "test: 仅1和2有正确返回")
    @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "int")
    public String deleteHistory(@RequestParam("id") Integer id) {
        int i = bookService.deleteBook(id);
        return "redirect:findAllBook";
    }
    @RequestMapping(value = "findAllById",method = RequestMethod.GET)
    @ApiOperation(value = "根据id查找书的信息")
    public String findBookById(@RequestParam("id") Integer id, Model model){
        Book book = bookService.findById(id);
        model.addAttribute("book",book);
        return "updatebook";
    }
    @RequestMapping(value = "updateBook")
    @ApiOperation(value = "修改书的信息")
    public String update(Book book){
        System.out.println(book);
        bookService.updateBook(book);
        return "redirect:findAllBook";
    }

}