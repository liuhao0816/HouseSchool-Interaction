package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;
import com.gxa.modules.sys.service.LeaveListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "请假记录")
@RestController
@Slf4j
public class LeaveListController {


    @Autowired
    private LeaveListService leaveListService;

    @GetMapping("/LeaveList")
    @ApiOperation("查询所有记录")
    public Result<List<LeaveList>> queryLeaveListAll1(){

            List<LeaveList> leaveLists=leaveListService.queryLeaveListAll1();
            Result<List<LeaveList>> result=new Result().ok(leaveLists);
            return result;
    }

    @GetMapping("/leaveList")
    @ApiOperation("多条件查询")
    public Result<List<LeaveList>> queryLeaveListBy(LeaveListDto leaveListDto){

        List<LeaveList> leaveLists=leaveListService.queryLeaveListBy(leaveListDto);
        Result<List<LeaveList>> result=new Result().ok(leaveLists);
        System.out.println(leaveListDto);
        return result;
    }


    @PostMapping("/leaveList")
    @ApiOperation("添加请假")
    public Result addLeaveList(){

            return null;
    }


    @PutMapping("/leaveList")
    @ApiOperation("撤销")
    public Result updateLeaveList(){

            return null;
    }

    @GetMapping("/leaveLists/{id}")
    @ResponseBody
    @ApiOperation("根据id查询")
    public Result<List<LeaveList>> queryById(@PathVariable("id") Integer id){

        List<LeaveList> leaveList=leaveListService.queryById(id);
        Result<List<LeaveList>> result=new Result().ok(leaveList);
        return result;
    }




}
