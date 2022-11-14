package com.gxa.modules.sys.controller;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.form.VoteSubmitForm;
import com.gxa.modules.sys.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Slf4j
@Api(tags = "V-投票管理接口")
public class VoteController {

    @Autowired
    private VoteService voteService;

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

    @PostMapping(value = "/sys/vote/submit")
    @ApiOperation(value = "投票管理-进行中投票活动参与投票")
    public Result<VoteList> submitVote(@RequestBody VoteSubmitForm voteSubmitForm){

        Result<VoteList> result = this.voteService.submitVote(voteSubmitForm);
        return result;
    }

    @ApiOperation(value = "投票管理-删除投票")
    @GetMapping(value = "/sys/vote/delete")
    public Result deleteVote(@RequestParam Integer voteId){

        Result result = new Result().error();
        try {
            this.voteService.deleteVote(voteId);
            result = new Result<>().ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "投票管理-发起投票")
    @PostMapping(value = "/sys/vote/add")
    public Result addVote(@RequestBody VoteList voteList){
        System.out.println("-----------"+ voteList);

        Result result = new Result<>().ok();
        return result;
    }

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
}
