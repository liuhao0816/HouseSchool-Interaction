package com.gxa.modules.sys.controller;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 8:54.
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gxa.common.utils.JsonResult;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.dto.NOtifyContent;
import com.gxa.modules.sys.form.NotifyTypeForm;
import com.gxa.modules.sys.mapper.NotifyTypeMapper;
import com.gxa.modules.sys.service.NotifyContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
@Api(tags = "消息统计接口")
@RestController
@RequestMapping("//notifyContent")
public class NotifyContentController {

    //@Autowired
    //NotifyTypeMapper notifyTypeMapper;
    //@ApiOperation(value="查询所有通知消息")

    //public Map selectAllMessage(Integer id){
    //    LambdaQueryWrapper<NOtifyContent> lambda = Wrappers.lambdaQuery(NOtifyContent.class);
    //    lambda.eq(NOtifyContent::getId,id );
    //    List<NOtifyContent> nOtifyContentList = NotifyContentService.list(lambda);

        //JsonResult jsonResult = new JsonResult();
        //jsonResult.setCode("200");
        //jsonResult.setMsg("查找成功");
        //jsonResult.setData(nOtifyContentList);
        //return jsonResult.getMap();

    //}




}
