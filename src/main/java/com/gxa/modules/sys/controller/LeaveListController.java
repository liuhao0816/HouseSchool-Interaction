package com.gxa.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.AllLeaveListDto;
import com.gxa.modules.sys.dto.LeaveListDto;
import com.gxa.modules.sys.entity.LeaveList;
import com.gxa.modules.sys.service.LeaveListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    public Result<List<AllLeaveListDto>> queryLeaveListAll1() {
        List<AllLeaveListDto> allLeaveListDtos = leaveListService.queryLeaveListAll1();
        Result<List<AllLeaveListDto>> result = new Result().ok(allLeaveListDtos);
        return result;
    }

    @PostMapping("/leaveList")
    @ApiOperation("多条件查询")
    public Result<List<AllLeaveListDto>> queryLeaveListBy(@RequestBody LeaveListDto leaveListDto) {

        Result<List<AllLeaveListDto>> result = this.leaveListService.queryLeaveListBy(leaveListDto);
        return result;
    }


    @PostMapping("/addLeaveList")
    @ApiOperation("添加请假")
    public Result addLeaveList(@RequestBody LeaveList leaveList) {

        log.debug("----{}---", leaveList);
        List<LeaveList> leaveLists = leaveListService.addLeaveList(leaveList);
        Result<List<LeaveList>> result = new Result().ok(leaveLists);
        return result;
    }



    @ApiOperation(value = "删除")
    @PostMapping("/leaveLists")
    public Result delete(Integer id) {
        return this.leaveListService.delete(id);
    }

    @GetMapping("/leaveLists/{id}")
    @ResponseBody
    @ApiOperation("根据id查询")
    public Result<List<AllLeaveListDto>> queryById(@PathVariable("id") Integer id) {

        List<AllLeaveListDto> allLeaveListDtos = leaveListService.queryById(id);
        Result<List<AllLeaveListDto>> result = new Result().ok(allLeaveListDtos);
        return result;
    }

    @GetMapping("/AddLeaveList")
    @ResponseBody
    @ApiOperation("根据id查询学生id和姓名")
    public Result<List<AllLeaveListDto>> queryByUserId(@RequestParam Integer user_id) {

        List<AllLeaveListDto> allLeaveListDtos = leaveListService.queryByUserId(user_id);
        Result<List<AllLeaveListDto>> result = new Result().ok(allLeaveListDtos);
        return result;
    }


    @PutMapping("/leaveLists")
    @ApiOperation("审核已审核")
    public Result updateByIdAll(@RequestParam Integer id,@RequestParam Integer user_id) {
        this.leaveListService.updateByIdAllT(id,user_id);
        return new Result<>().ok("审核通过");
    }

    @PutMapping("/leaveListsF")
    @ApiOperation("审核未通过")
    public Result updateByIdAllF(@RequestParam Integer id,@RequestParam Integer user_id) {
        this.leaveListService.updateByIdAllF(id,user_id);
        return new Result<>().ok();
    }

}