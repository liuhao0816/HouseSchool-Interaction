package com.gxa.modules.sys.controller;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 8:54.
 */

import com.gxa.common.utils.JsonResult;
import com.gxa.modules.sys.service.NotifyContentService;
import com.gxa.modules.sys.service.NotifyTypeService;
import com.gxa.modules.sys.service.VoteStatService;
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
@RequestMapping("/voteStat")
public class VoteStatController {

    @Autowired
    VoteStatService voteStatService;

    @Autowired
    NotifyTypeService notifyTypeService;

    @ApiOperation(value="查询所有投票发布")
    @PostMapping("/selectAll")
    public Map selectAll() {
        List list = voteStatService.selectAll();
        JsonResult jsonResult=new JsonResult();
        jsonResult.setData(list);
        jsonResult.setCode("200");
        jsonResult.setMsg("查询成功");
        return jsonResult.getMap();

    }
    @ApiOperation(value = "消息发布列表")
    @PostMapping("/type")
    public Map selectByType() {
        Map map1 = notifyTypeService.selectByType();
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("200");
        jsonResult.setMsg("查询成功");
        jsonResult.setData(map1);
        return jsonResult.getMap();
    }



}
