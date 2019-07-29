package com.qf.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
        *     课程表实体类
        */
@Data
public class Course implements Serializable {
    private Integer courseId;
    private String courseTitleImgurl ;                  //课程表面图片
    private String courseSubtitle  ;                  //书标题
    private String courseSummary;                   //书内容摘要
    private String courseAuthor;                    //作者
    private Integer courseClassNum;                 //总共多少讲
    private Double courseSprice;                    //价格
    private Integer courseStudyPeoples;             //加入学习人数
    private String courseImgurl;                    //界面的书图片
    private String courseOtherImgurl;               //详情页置顶作者的图片
    private String courseTeacherIntro;               //老师简介
    private String courseFeatureIntroI;              //课程简介的上层
    private String courseFeatureIntroII;             //课程简介的下层
    private String courseTitle;                     //发刊词内容
    private Integer coursePeoples;                   //学习过的人数               //外键（新建目录表存储每本书的目录以及每个目录对应音频地址
    private String courseTableImgurl;               //课程表图片
    private String coursePurchaseAttention;          //购买须知
    private String courseCollege;                    //学院
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date courseUpdateTime;                  //课程更新时间
    private Integer courseClassId;

    private String topImage;        //专题详情页图片  (上)(师帅)
    private String bottomImage;      //专题详情页图片（下）（师帅）
    private String coursePurchaseAttentionTwo;//(师帅)
    private String coursePurchaseAttentionThree;//(师帅)
    private int id;//不存如数据库，勿删除！
    private Integer type;

}