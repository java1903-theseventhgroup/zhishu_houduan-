package com.qf.domain;

import lombok.Data;

import java.util.Date;

        /**
         *   听书表
         */
@Data
public class Listen {
    private Integer listenId;
    private  String listenSubtitle;         //书名
    private Double listenPrice;            //听书价格
    private String listenAudioTime;            //音频时长
    private String listenAuthor;             //解读人（作者
    private String listenContent;            //音频内容简介（长
    private String listenPublisherIntro;     //出版社介绍
    private String listenAudioIntro;          //音频简介（短）
    private String listenAuthorPhoto;        //解读人图像（(七牛云url地址
    private String listenAuthorValidity;     //解读人简介
    private Integer listenCount;             //收听人数
    private String listenType;               //类型（精选书单（0）/新书上架(1)/猜你喜欢(2) 任意一个
    private String listenAudio;              //音频地址（尝试七牛云存储地址）
    private String listenImgUrl;             //音频图片路径(七牛云url地址)
    private Integer type;
    private Integer listenClassId;
}
