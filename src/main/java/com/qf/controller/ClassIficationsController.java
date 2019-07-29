/*
package com.qf.controller;

import com.qf.response.QueryResponseResult;
import com.qf.service.ClassIficationsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class ClassIficationsController {
    @Autowired
    private ClassIficationsService classIficationsService;

    @RequestMapping(value = "findSujectsByName",method = RequestMethod.GET)
    @ApiOperation(value = "查询所有二级目录下的全部")
    public QueryResponseResult findSujectsByName(@RequestBody String subject){
        QueryResponseResult sujectsByName = classIficationsService.findSujectsByName(subject);
        return sujectsByName;
    }
}
*/
