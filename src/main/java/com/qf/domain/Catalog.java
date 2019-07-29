package com.qf.domain;

import lombok.Data;

/**
 *    课程对应目录表
 */
import java.io.Serializable;

@Data
public class Catalog implements Serializable {
    private Integer catalog;                  //
    private String catalogName;                //目录名称
    private String catalogTime;               //音频时长
    private String catalogUrl;                 //对应音频存储地址
    private Integer  catalogCourseId;                    //外键 （绑定对应课程）
}
