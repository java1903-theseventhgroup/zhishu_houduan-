package com.qf.service.serviceImpl;

import com.google.gson.Gson;
import com.qf.dao.PictureDao;

import com.qf.domain.Picture;
import com.qf.service.FileService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：
 * @ClassName ：FileServiceImpl
 * @date :
 * @description :
 */
@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private Auth auth;


    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;
    @Autowired
    private StringMap putPolicy;
    @Autowired
    private PictureDao pictureDao;
    @Override
    public Map uploadFile(File file) throws QiniuException {
        Map map = new HashMap();
        Response response = this.uploadManager.put(file,null,getUploadToken());
        //解析上传的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);

        String imageName = putRet.hash;
        System.out.println(imageName);
        //拿到七牛的URL 存到数据库中
        Picture picture=new Picture();
        picture.setPicPath(path+"/"+imageName);
        picture.setPicName("图片");
        pictureDao.insertPicture(picture);

        int retry = 0;
        while(response.needRetry() && retry < 3){
            response = this.uploadManager.put(file,null,getUploadToken());
        }
        map.put("response",response);
        map.put("imgName",picture.getPicPath());
        return map;
    }




    private String getUploadToken(){
        return this.auth.uploadToken(bucket,null,3600,putPolicy);
    }



}


