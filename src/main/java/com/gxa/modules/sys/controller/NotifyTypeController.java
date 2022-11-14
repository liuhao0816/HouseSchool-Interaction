package com.gxa.modules.sys.controller;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.NotifyType;
import com.gxa.modules.sys.form.NotifyTypeForm;
import com.gxa.modules.sys.service.NotifyTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/11
 */
@Api(tags = "通知类型接口")
@RestController
@Slf4j
@RequestMapping("/notifyType")
public class NotifyTypeController {

    @Resource
    private NotifyTypeService notifyTypeService;

    @ApiOperation(value="分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int",required = true),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int",required = true),

    })
    @GetMapping("list")
    public Result<NotifyTypeForm> list02(@RequestParam @ApiIgnore Map<String,Object> params){
        PageUtils pageUtils = this.notifyTypeService.queryByPage(params);
        return new Result().ok(pageUtils);
    }


    @ApiOperation(value="添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "type",value ="类型",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "attribute",value ="属性",dataType ="String"),

    })
    @PostMapping("/add")
   public Result addNotifyType(@ApiIgnore NotifyTypeForm notifyTypeForm){
        return notifyTypeService.add(notifyTypeForm);
   }

   @ApiOperation(value="修改接口")
   @ApiImplicitParams({
           @ApiImplicitParam(paramType = "query",name = "id",value ="序号",dataType ="int",required = true),
           @ApiImplicitParam(paramType = "query",name = "type",value ="类型",dataType ="String"),
           @ApiImplicitParam(paramType = "query",name = "attribute",value ="属性",dataType ="String"),
   })
   @PostMapping("/update")
   public Result update(@ApiIgnore NotifyTypeForm notifyTypeForm){
       return this.notifyTypeService.update(notifyTypeForm);
   }

   @ApiOperation(value="根据id查询接口")
   @ApiImplicitParam(paramType = "query",name = "id",value ="序号",dataType ="int",required = true)
   @PostMapping("/queryById")
   public Result<NotifyTypeForm> queryById(Integer id){
      return this.notifyTypeService.getNotifyTypeById(id);


   }

    @ApiOperation(value="删除接口")
    @ApiImplicitParam(paramType = "query",name = "id",value ="序号",dataType ="int",required = true)
    @PostMapping("/delete")
    public Result delete(Integer id){
        return this.notifyTypeService.delete(id);
    }

    @ApiOperation(value="查询所有通知类型接口")
    @GetMapping("/queryTypeName")
    public Result<NotifyTypeForm> queryTypeName(){
        return this.notifyTypeService.queryTypeName();
    }

    @ApiOperation(value="修改状态接口")
    @GetMapping("/updateStatus")
    public Result updateStatus(Integer id){
        return this.notifyTypeService.updateStatus(id);
    }
}
