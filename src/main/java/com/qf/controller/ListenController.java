package com.qf.controller;

import com.qf.domain.Listen;
import com.qf.response.QueryResponseResult;

import com.qf.service.FileService;
import com.qf.service.ListenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("listen")
public class ListenController {
    @Autowired
    private ListenService listenService;
    @Autowired
    private FileService fileService;
    @Value("${baseUploadUrl}")
    private String url;

    // /**
    //  *
    //  * @return
    //  */
    // @RequestMapping(value="laodAll" ,method = RequestMethod.GET)
    // @ApiOperation(value = "查询听书全部（baizhenjia）" ,notes ="返回听书的所有数据" )
    // public QueryResponseResult loadAll(){
    //     return listenService.loadAll();
    // }

    /**
     *
     * @param （精选书单（0）/新书上架(1)/猜你喜欢(2) 任意一个
     * @return
     */
    @RequestMapping(value="selsctByListenType" ,method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "根据听书类型（精选书单（0）/新书上架(1)/猜你喜欢(2) 返回听书" ,notes ="baizhenjia）" )
    public QueryResponseResult selsctByListenType(@RequestParam() String listenType ){
        return listenService.selsctByListenType(listenType);
    }

    /**
     *
     * @param listenAuthor
     * @return
     */

    @RequestMapping(value="selsctByListenAuthor" ,method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "根据听书作者" ,notes ="baizhenjia）" )
    public QueryResponseResult selsctByListenAuthor(@RequestParam String listenAuthor ){
        return listenService.selsctByListenType(listenAuthor);
    }

     //后台查询所有用户
    @RequestMapping(value = "/loadAll",method = RequestMethod.GET)
    @ApiOperation("后台查询听书")
    public String loadAll(Model model, @RequestParam(required = false,defaultValue = "1")Integer page,
                          @RequestParam(required = false,defaultValue = "5")Integer rows
    ){
        int maxpage=listenService.selectRows(rows);
        if (page<1){page=1;}
        if(page>maxpage){page=maxpage;}
        List<Listen> listens = listenService.loadAll(page, rows);
        model.addAttribute("maxpage",maxpage);
        model.addAttribute("page",page);
        model.addAttribute("listensList",listens);
        return "listen";
    }

    @RequestMapping(value = "addListen",method = RequestMethod.POST)
    @ApiOperation(value = "添加一本听书")
    public String addListen(Listen listen){
        boolean b = listenService.addListen(listen);
        return b?"redirect:/listen/loadAll":"error";
    }

    @RequestMapping(value = "deleteListen")
    @ApiOperation(value = "删除一本听书")
    public String deleteListen(@RequestParam("listenId") Integer listenId){
        boolean b = listenService.deleteListen(listenId);
        return "redirect:/listen/loadAll";
    }

    @RequestMapping(value = "updateListen",method = RequestMethod.POST)
    @ApiOperation(value = "修改一本听书")
    public String updateListen(Listen listen){
        boolean b = listenService.updateListen(listen);
        return "redirect:/listen/loadAll";
    }

    @RequestMapping(value = "findListenById")
    @ApiOperation(value = "通过Id查找到要修改的听书")
    public String findListenById(@RequestParam("listenId") Integer listenId,Model model){
        Listen list = listenService.findListenById(listenId);
        model.addAttribute("l",list);
        return "updatelisten";
    }

    @RequestMapping(value = "addPhoto")
    @ResponseBody
    @ApiOperation(value = "后端接口上传图片")
    public String addPhoto(@RequestParam(value = "listenImgUrl") MultipartFile multipartFiles,Model model) {
        String fileName = multipartFiles.getOriginalFilename();
        System.out.println(fileName);
        File file = new File(url+fileName);
        try{
            multipartFiles.transferTo(file);
            Map response = fileService.uploadFile(file);
            Object imageName = response.get("imgName");

          /*  Listen listen=new Listen();
            listen.setListenImgUrl((String)imageName);
            listen.setListenId(listenId);
            listenService.uploadListenImgUrl(listen);*/
        }catch (Exception e){
            e.printStackTrace();
        }

        return "imageName";
    }


    @RequestMapping(value = "uploadListenAudio")
    @ApiOperation(value = "后端接口上传音频")
    public String uploadListenAudio(Integer listenId,@RequestParam(value = "listenAudio") MultipartFile multipartFiles) {
        String fileName = multipartFiles.getOriginalFilename();
        File file = new File(url+fileName);
        try{
            multipartFiles.transferTo(file);
            Map response = fileService.uploadFile(file);
            Object imageName = response.get("imgName");
            Listen listen=new Listen();
            listen.setListenAudio((String)imageName);
            listen.setListenId(listenId);
            listenService.uploadListenAudio(listen);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "成功";
    }
}
