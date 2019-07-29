package com.qf.service;

import com.qiniu.common.QiniuException;

import java.io.File;
import java.util.Map;

public interface FileService {

    /**
     * @Author :bai
     * @Description :
     * @Date :
     * @Param :
     * @return :
     **/
    Map uploadFile(File file) throws QiniuException;


}


