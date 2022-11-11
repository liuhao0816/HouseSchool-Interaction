package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.LeaveList;
import com.gxa.modules.sys.service.LeaveListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "请假记录")
@RestController

public class LeaveListController {


    @Autowired
    private LeaveListService leaveListService;

    @GetMapping("/LeaveList")
    @ApiOperation("查询所有记录")
    public Result<List<LeaveList>> queryLeaveListAll1(){

            List<LeaveList> leaveLists=leaveListService.queryLeaveListAll1();
            Result<List<LeaveList>> result=new Result().ok(leaveLists);
        System.out.println(leaveLists);
            return result;


    }

}
