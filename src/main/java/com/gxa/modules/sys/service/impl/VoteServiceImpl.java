package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.*;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.form.VoteSubmitForm;
import com.gxa.modules.sys.mapper.VoteMapper;
import com.gxa.modules.sys.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
@Service
@Slf4j
public class VoteServiceImpl extends ServiceImpl<VoteMapper,VoteList> implements VoteService {

//    @Autowired
//    private VoteMapper voteMapper;
    @Override
    public List<VoteList> queryNo() {
        List<VoteList> voteLists = baseMapper.queryNo();
//        log.info("---查询结果---{}---",voteLists);
//        for (int i = 0;i < voteLists.size();i++){
//            VoteList voteList = voteLists.get(i);
//            String voteExplain = voteList.getVoteExplain();
//
//            String str = HtmlUtils.htmlUnescape(voteExplain);
//            voteList.setVoteExplain(str);
//        }
        return voteLists;
    }

    @Override
    public PageUtils queryAllVote(VoteForm voteForm) {
        Long page = voteForm.getPage().longValue();
        Long limit = voteForm.getLimit().longValue();

        Page<VoteList> listPage = new Page<VoteList>(page,limit);

        IPage<VoteList> voteListIPage = baseMapper.queryAllVote(listPage,voteForm);
        List<VoteList> voteLists = voteListIPage.getRecords();
        for(int i = 0;i < voteLists.size();i++){
            VoteList voteList = voteLists.get(i);
            List<Role> roles = this.baseMapper.queryRole(voteList.getVoteId());
            voteList.setVoteRoles(roles);
        }

        PageUtils pageUtils = new PageUtils(voteListIPage);
        return pageUtils;
    }

    @Override
    public VoteList queryByVoteId(Integer voteId) {
        VoteList voteList = this.baseMapper.queryByVoteId(voteId);
        List<VoteOption> voteOptions = voteList.getVoteOptions();

        for(int i = 0;i < voteOptions.size();i++){
            VoteOption voteOption = voteOptions.get(i);
            int id = voteOption.getId();

            //根据投票选项Id，统计投票票数
            Integer integer = this.baseMapper.countOptionPoll(id);
            voteOption.setVoteOptionPoll(integer);
        }

        return voteList;
    }

    @Override
    public Result<VoteList> submitVote(VoteSubmitForm voteSubmitForm) {
        Result<VoteList> result = new Result().error();
        Integer userId = voteSubmitForm.getUserId();
        Integer voteId = voteSubmitForm.getVoteId();
        List<Integer> voteOptionIds = voteSubmitForm.getVoteOptionIds();
        VoteList voteList = this.baseMapper.queryByVoteId(voteId);

        String voteScope = voteList.getVoteScope();
        List<Role> voteRoles = voteList.getVoteRoles();

        if(voteScope.equals("全校")){
            if(voteRoles.size() == 1){
                if(voteRoles.get(0).getRoleId() == 2){
                    Teacher teacher = this.baseMapper.queryByUserId(userId);
                    if(teacher != null){
                        Integer integer = this.baseMapper.queryIsPoll(voteId, userId);
                        if (integer != null){
                            result = new Result().error(ErrorCode.NOT_NULL,"您已参与过该投票活动，不能重复投票！");
                        }else {
                            for (int i = 0;i < voteOptionIds.size();i++){
                                Integer voteOptionId = voteOptionIds.get(i);
                                this.baseMapper.addSubmitVote(voteOptionId,userId);
                            }

                            VoteList voteList01 = this.baseMapper.queryByVoteId(voteId);
                            List<VoteOption> voteOptionss = voteList01.getVoteOptions();

                            for(int i = 0;i < voteOptionss.size();i++){
                                VoteOption voteOption = voteOptionss.get(i);
                                int id = voteOption.getId();

                                //根据投票选项Id，统计投票票数
                                Integer intid = this.baseMapper.countOptionPoll(id);

                                voteOption.setVoteOptionPoll(intid);
                            }
                            result = new Result().ok(voteList01);
                        }
                    }else {
                        result = new Result().error(ErrorCode.NOT_NULL,"您不属与参与投票对象！");
                    }
                }else if(voteRoles.get(0).getRoleId() == 3){
                    StudentParent studentParent = this.baseMapper.queryByParentUserId(userId);
                    if(studentParent != null){
                        Integer integer = this.baseMapper.queryIsPoll(voteId, userId);
                        if (integer != null){
                            result = new Result().error(ErrorCode.NOT_NULL,"您已参与过该投票活动，不能重复投票！");
                        }else {
                            for (int i = 0;i < voteOptionIds.size();i++){
                                Integer voteOptionId = voteOptionIds.get(i);
                                this.baseMapper.addSubmitVote(voteOptionId,userId);
                            }

                            VoteList voteList01 = this.baseMapper.queryByVoteId(voteId);
                            List<VoteOption> voteOptions = voteList01.getVoteOptions();

                            for(int i = 0;i < voteOptions.size();i++){
                                VoteOption voteOption = voteOptions.get(i);
                                int id = voteOption.getId();

                                //根据投票选项Id，统计投票票数
                                Integer intid = this.baseMapper.countOptionPoll(id);

                                voteOption.setVoteOptionPoll(intid);
                            }
                            result = new Result().ok(voteList01);
                        }
                    }else {
                        result = new Result().error(ErrorCode.NOT_NULL,"您不属与参与投票对象！");
                    }
                }
            }else if(voteRoles.size() == 2){
                Teacher teacher = this.baseMapper.queryByUserId(userId);
                System.out.println("1-------->"+ teacher);
                StudentParent studentParent = this.baseMapper.queryByParentUserId(userId);
                System.out.println("2-------->"+ studentParent);

                if(teacher != null || studentParent != null){
                    Integer integer = this.baseMapper.queryIsPoll(voteId, userId);
                    System.out.println("3-------->"+ integer);
                    if (integer != null){
                        result = new Result().error(ErrorCode.NOT_NULL,"您已参与过该投票活动，不能重复投票！");
                        System.out.println("1111111111111111111111111111111111111");
                    }else {
                        for (int i = 0;i < voteOptionIds.size();i++){
                            Integer voteOptionId = voteOptionIds.get(i);
                            this.baseMapper.addSubmitVote(voteOptionId,userId);
                        }

                        VoteList voteList01 = this.baseMapper.queryByVoteId(voteId);
                        List<VoteOption> voteOptions = voteList01.getVoteOptions();

                        for(int i = 0;i < voteOptions.size();i++){
                            VoteOption voteOption = voteOptions.get(i);
                            int id = voteOption.getId();

                            //根据投票选项Id，统计投票票数
                            Integer intid = this.baseMapper.countOptionPoll(id);

                            voteOption.setVoteOptionPoll(intid);
                        }
                        result = new Result().ok(voteList01);
                    }
                }
            }
        }else if(voteScope.equals("指定班级")){

        }

        return result;
    }

    @Override
    public void deleteVote(Integer voteId) {
        this.baseMapper.updateVoteIsDelete(voteId);
    }

    @Override
    public List<VoteList> queryVoteByUserId(Integer userId) {
        List<VoteList> voteLists = this.baseMapper.queryVoteByUserId(userId);
        return voteLists;
    }
}
