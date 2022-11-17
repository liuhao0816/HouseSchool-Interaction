package com.gxa.modules.sys.controller;

import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.form.VoteSubmitForm;
import com.gxa.modules.sys.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@RestController
@Slf4j
@Api(tags = "V-投票管理接口")
public class VoteController {

    @Autowired
    private VoteService voteService;

//    @RequiresPermissions("sys:vote:list")
    @ApiOperation(value = "投票管理-列表/条件查询接口")
    @PostMapping(value = "/sys/vote/list")
    public Result<PageUtils> queryAllVote(@RequestBody VoteForm voteForm){
        System.out.println("查询条件为：-----》"+ voteForm);

        Result<PageUtils> result = new Result().error();
        try {
            PageUtils pageUtils = this.voteService.queryAllVote(voteForm);

            result = new Result<PageUtils>().ok(pageUtils);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

//    @RequiresPermissions("sys:vote:title")
    @ApiOperation(value = "投票管理-标题链接接口")
    @GetMapping(value = "/sys/vote/title")
    public Result<VoteList> queryByVoteId(@RequestParam Integer voteId){

        Result<VoteList> result = new Result().error();
        try {
            VoteList voteList = this.voteService.queryByVoteId(voteId);
            result = new Result<VoteList>().ok(voteList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

//    @RequiresPermissions("sys:vote:submit")
    @PostMapping(value = "/sys/vote/submit")
    @ApiOperation(value = "投票管理-进行中投票活动参与投票")
    public Result<VoteList> submitVote(@RequestBody VoteSubmitForm voteSubmitForm){

        Result<VoteList> result = this.voteService.submitVote(voteSubmitForm);
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "voteId",value = "投票活动ID",dataType = "Integer"),
            @ApiImplicitParam(paramType = "query",name = "userId",value = "当前登录用户ID",dataType = "Integer")
    })
//    @RequiresPermissions("sys:vote:delete")
    @ApiOperation(value = "投票管理-删除投票")
    @PostMapping(value = "/sys/vote/delete")
    public Result deleteVote(@RequestParam @ApiIgnore Map<String,String> params){
        Result result = new Result().error();
        System.out.println("-----------params-----" + params);
        String userIdstr = params.get("userId");
        int userId = Integer.valueOf(userIdstr);
        String voteIdstr = params.get("voteId");
        int voteId = Integer.valueOf(voteIdstr);

        VoteList voteList = this.voteService.queryByVoteId(voteId);
        int userId11 = voteList.getUser().getUserId();
        System.out.println("----------+userId11+-----" + userId11);
        if(userId == userId11){
            try {
                this.voteService.deleteVote(voteId);
                result = new Result<>().ok();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            result = new Result<>().error(ErrorCode.UNAUTHORIZED,"您没有权限删除该投票活动");
        }
        return result;
    }

    @RequiresPermissions("teacher")
    @ApiOperation(value = "投票管理-发起投票")
    @PostMapping(value = "/sys/vote/add")
    public Result addVote(@RequestBody VoteList voteList){
        System.out.println("-----------"+ voteList);

        Result result = this.voteService.addVoteTable(voteList);

        return result;
    }

//    @RequiresPermissions("sys:vote:mesponsor")
    @ApiOperation(value = "投票管理-小程序-我发起的")
    @GetMapping(value = "/app/vote/meSponsor")
    public Result<List<VoteList>> queryMeVote(@RequestParam Integer userId){
        Result<List<VoteList>> result = new Result().error();

        try {
            List<VoteList> voteLists = this.voteService.queryVoteByUserId(userId);
            result = new Result<List<VoteList>>().ok(voteLists);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "投票管理-小程序-投票列表")
    @GetMapping(value = "/app/vote/nocondition")
    public Result<List<VoteList>> queryAllVoteNo(){
        List<VoteList> voteLists = this.voteService.queryNo();
        Result<List<VoteList>> result = new Result<List<VoteList>>().ok(voteLists);
        return result;
    }
}
