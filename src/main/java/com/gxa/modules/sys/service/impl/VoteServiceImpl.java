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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

            //获取现在的时间戳
            Date date = new Date();
            Long nowTime = date.getTime();

            //获取该条活动的截止时间
            Date voteEndTime = voteList.getVoteEndTime();
            long endTime = voteEndTime.getTime();

            //判断该活动状态为进行中还是已经结束
            if(nowTime <= endTime){
                voteList.setVoteStatus(1);
            }else {
                voteList.setVoteStatus(2);
            }
            List<Role> roles = this.baseMapper.queryRole(voteList.getVoteId());
            voteList.setVoteRoles(roles);

            List<String> roleNames = new ArrayList<>();
            for(int j = 0;j < roles.size();j++){
                Role role = roles.get(j);
                String roleName = role.getRoleName();
                roleNames.add(roleName);
            }
            //将该数组转换成字符串
            String str = StringUtils.join(roleNames,",");
            voteList.setRoleStr(str);
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
            //根据投票活动id，获取发布老师的userId
            Integer teacherUserId = this.baseMapper.queryUserIdByVoteId(voteId);
            //根据发布者老师的id获取该班级学生对应家长的userId
            List<Integer> studentParents = this.baseMapper.queryUserIdsByUserId(teacherUserId);

            if(Arrays.asList(studentParents).contains(userId)){
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
            }else {
                result = new Result().error(ErrorCode.NOT_NULL,"您不属与参与投票对象！");
            }
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


    @Override
    public Result addVoteTable(VoteList voteList) {
        Result result = new Result<>().error();
        //获取投票选项
        List<VoteOption> voteOptions = voteList.getVoteOptions();
        //获取参与对象
        List<Role> voteRoles = voteList.getVoteRoles();

        voteList.setVoteStatus(1);

//        String voteExplain = voteList.getVoteExplain();
//        String str = HtmlUtils.htmlEscapeHex(voteExplain);
//        voteList.setVoteExplain(str);

        //获取当前的时间
        Date date = new Date();
        voteList.setVoteStartTime(date);

        try {
            //获取数据库中投票活动id最大值，生成投票活动的Id
            Integer voteIdMax = this.baseMapper.queryVoteIdMax();
            System.out.println("--------zuidazhi-----" + voteIdMax);

            //新发布投票活动id为最大值加一
            Integer voteId = voteIdMax + 1;
            voteList.setVoteId(voteId);

            this.baseMapper.addVoteTable(voteList);
            for(int i = 0;i < voteOptions.size();i++){
                VoteOption voteOption111 = voteOptions.get(i);
                String voteOption = voteOption111.getVoteOption();
                this.baseMapper.addVoteOption(voteOption,voteId);
            }
            for(int j = 0;j < voteRoles.size();j++){
                Role role111 = voteRoles.get(j);
                int roleId = role111.getRoleId();
                this.baseMapper.addVoteRole(voteId,roleId);
            }
            result = new Result<>().ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
