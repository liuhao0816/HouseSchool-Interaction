package com.gxa.modules.sys.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.Role;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.entity.VoteOption;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.form.VoteSubmitForm;
import com.gxa.modules.sys.service.VoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

@RestController
@Slf4j
@Api(tags = "V-投票管理接口")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @ApiOperation(value = "投票管理-列表/条件查询接口")
    @PostMapping(value = "/sys/vote/list")
    public Result<List<VoteList>> queryAllVote(@RequestBody VoteForm voteForm){
        System.out.println("查询条件为：-----》"+ voteForm);
        Integer page = voteForm.getPage();
        Integer limit = voteForm.getLimit();

        Result<List<VoteList>> result = new Result().error();
        try {
            PageHelper.startPage(page,limit);
            List<VoteList> voteLists = this.voteService.queryAllVote(voteForm);

            PageInfo<VoteList> pageInfo = new PageInfo<>(voteLists);
            result = new Result().ok(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    @ApiOperation(value = "投票管理-标题链接接口")
    @GetMapping(value = "/sys/vote/title")
    public Result<VoteList> queryByVoteId(@RequestParam Integer voteId){
        List<VoteOption> voteOptions = new ArrayList<>();
        VoteOption voteOption1 = new VoteOption();
        voteOption1.setId(1);
        voteOption1.setVoteOption("月饼");
        voteOption1.setVoteOptionPoll(55);
        VoteOption voteOption2 = new VoteOption();
        voteOption2.setId(2);
        voteOption2.setVoteOption("消费卷");
        voteOption2.setVoteOptionPoll(44);
        voteOptions.add(voteOption1);
        voteOptions.add(voteOption2);

        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setRoleId(10);
        role1.setRoleName("家长");
        Role role2 = new Role();
        role2.setRoleId(11);
        role2.setRoleName("老师");
        roles.add(role1);
        roles.add(role2);

        VoteList voteList = new VoteList();
        voteList.setVoteId(1);
        voteList.setVoteTitle("每天看书半小时活动投票");
        voteList.setVoteName("杨老师");
        Date date = new Date();
        voteList.setVoteStartTime(date);
        voteList.setVoteEndTime(date);
        String str = HtmlUtils.htmlEscapeHex("\n" +
                "近期，老师发现班上不少同学们对看书很感兴趣。因此老师考虑举办一个”每天看书半小时“的活动，让孩子们慢慢培养看书的习惯和增强探索知识的兴趣，现由家长们投票决定是否同意举办此活动，看书的时间是每天下午放学后半小时。");
        voteList.setVoteExplain(str);
        voteList.setVoteType(1);
        voteList.setVoteOptions(voteOptions);
        voteList.setVoteScope("全校");
        voteList.setVoteRoles(roles);

        Result<VoteList> result = new Result().ok(voteList);
        return result;
    }

    @PostMapping(value = "/sys/vote/submit")
    @ApiOperation(value = "投票管理-进行中投票活动参与投票")
    public Result<VoteList> submitVote(@RequestParam VoteSubmitForm voteSubmitForm){
        System.out.println("接收的参数为----》"+voteSubmitForm);

        List<VoteOption> voteOptions = new ArrayList<>();
        VoteOption voteOption1 = new VoteOption();
        voteOption1.setId(1);
        voteOption1.setVoteOption("月饼");
        voteOption1.setVoteOptionPoll(55);
        VoteOption voteOption2 = new VoteOption();
        voteOption2.setId(2);
        voteOption2.setVoteOption("消费卷");
        voteOption2.setVoteOptionPoll(44);
        voteOptions.add(voteOption1);
        voteOptions.add(voteOption2);

        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setRoleId(10);
        role1.setRoleName("家长");
        Role role2 = new Role();
        role2.setRoleId(11);
        role2.setRoleName("老师");
        roles.add(role1);
        roles.add(role2);

        VoteList voteList = new VoteList();
        voteList.setVoteId(1);
        voteList.setVoteTitle("每天看书半小时活动投票");
        voteList.setVoteName("杨老师");
        Date date = new Date();
        voteList.setVoteStartTime(date);
        voteList.setVoteEndTime(date);
        String str = HtmlUtils.htmlEscapeHex("\n" +
                "近期，老师发现班上不少同学们对看书很感兴趣。因此老师考虑举办一个”每天看书半小时“的活动，让孩子们慢慢培养看书的习惯和增强探索知识的兴趣，现由家长们投票决定是否同意举办此活动，看书的时间是每天下午放学后半小时。");
        voteList.setVoteExplain(str);
        voteList.setVoteType(1);
        voteList.setVoteOptions(voteOptions);
        voteList.setVoteScope("全校");
        voteList.setVoteRoles(roles);

        Result<VoteList> result = new Result().ok(voteList);
        return result;
    }

    @ApiOperation(value = "投票管理-删除投票")
    @GetMapping(value = "/sys/vote/delete")
    public Result deleteVote(@RequestParam Integer voteId){

        Result result = new Result<>().ok();
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
        System.out.println("---------------->>>>>>" + userId);

        Result<List<VoteList>> result = new Result().ok();
        return result;
    }
}
