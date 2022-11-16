package com.gxa.modules.sys.controller;


import com.gxa.common.utils.Result;
import com.gxa.modules.sys.form.NotifyForm;
import com.gxa.modules.sys.service.NotifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/11
 */
@Api(tags = "通知列表接口")
@RestController
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @ApiOperation(value="后台分页查询通知接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "startTime",value ="开始日期",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "endTime",value ="结束日期",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "type",value ="类型",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "scope",value ="发送范围",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "publisher",value ="发布者",dataType ="String"),

    })
    @GetMapping("/list")
    public Result list02(Integer page,Integer limit,@ApiIgnore NotifyForm notifyForm){

        List<NotifyForm> notifyForms = notifyService.queryByPage(page, limit, notifyForm);
        Result<Object> result = new Result<>();
        result.setTotal(notifyForms.size());
        result.setData(notifyForms);
        return result.ok();
    }

    @ApiOperation("删除接口")
    @ApiImplicitParam(paramType = "query",name = "id",value ="id",dataType ="int",required = true)
    @PostMapping("/delete")
    public Result delete(Integer id){
         return notifyService.delete(id);
    }

    @ApiOperation("添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "type",value ="通知类型",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "scope",value ="发送范围",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "publisher",value ="署名",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "release_time",value ="发布日期",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "title",value ="通知标题",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "content",value ="通知内容",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "picture",value ="图片",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "enclosure",value ="附件",dataType ="String"),
    })
    @PostMapping("/add")
    public Result add(@ApiIgnore  NotifyForm notifyForm){
        return this.notifyService.add(notifyForm);
    }

    @ApiOperation(value="查询通知内容接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "title",value ="通知标题",dataType ="String",required = true),
            @ApiImplicitParam(paramType = "query",name = "userName",value ="当前用户名",dataType ="String",required = true),
    })
    @GetMapping("/queryContentByTitle")
    public Result queryContentById(String title,String userName){

        NotifyForm notifyById = this.notifyService.getNotifyByTitle(title,userName);
        return new Result().ok(notifyById);
    }

    @ApiOperation(value="小程序根据当前用户查询通知列表接口")
    @ApiImplicitParam(paramType = "query",name = "userName",value ="当前用户名",dataType ="String")
    @GetMapping("/queryContentByUser")
    public Result queryContentByUser(String userName){
        return this.notifyService.queryNotifyByUser(userName);
    }

    @ApiOperation(value="查询年级列表接口")
    @GetMapping("/queryAllClass")
    public Result queryAllClass(){
        return this.notifyService.queryAllClass();
    }

}
