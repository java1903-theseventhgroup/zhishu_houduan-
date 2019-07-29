package com.qf.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
             * 电子书表
             */

@Data
public class Book {
    private Integer bookId;
    private String bookSubtitle;            //书名
    private String booKeditRecomend;        //编辑推荐语
    private Double bookSprice;               //图书价格
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookTime;                  //上架时间
    private String bookAuthor;              //作者名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bookData;                  //出版时间
    private String bookContent;             //内容简介
    private String bookPublisher;           //出版社名称
    private String bookPublisherIntro;      //出版社介绍
    private String bookIntro;               //内容简介
    private String bookTts;                 //是否可以朗读（是/否
    private String bookType;               //书籍类型（精选书单(0)/新书上架(1)/限时特价(2)/最近试读(3)/猜你喜欢(4)）",01234为定义的字段
    private String bookImgUrl;              //书图片路径(七牛云url地址)
    private String bookAuthorInfo;          //作者详细信息
    private Integer bookClassId;                //
    private Integer type=0;                //类型
    private ClassIfications bd;   //外键（绑定对应二级目录）

}
