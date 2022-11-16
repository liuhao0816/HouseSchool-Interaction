package com.gxa.modules.sys.controller;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 8:54.
 */

import com.gxa.common.utils.JsonResult;
import com.gxa.modules.sys.service.NotifyContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/notifyContent")
public class NotifyContentController {

    @Autowired

    NotifyContentService notifyContentService;

    @ApiOperation(value="查询所有通知发布")
    @PostMapping("/selectAll")
    public Map selectAll() {
        List list = notifyContentService.selectAll();
        JsonResult jsonResult=new JsonResult();
        jsonResult.setData(list);
        jsonResult.setCode("200");
        jsonResult.setMsg("查询成功");
        return jsonResult.getMap();

    }




}
