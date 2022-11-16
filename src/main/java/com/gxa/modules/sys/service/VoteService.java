package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.form.VoteSubmitForm;

import java.util.List;

public interface VoteService extends IService<VoteList> {
    //测试无条件查询投票列表
    public List<VoteList> queryNo();
    //条件查询投票列表
    public PageUtils queryAllVote(VoteForm voteForm);
    //根据投票活动id，查询该活动详情
    public VoteList queryByVoteId(Integer voteId);
    //指定的参投票对象，参与投票
    public Result<VoteList> submitVote(VoteSubmitForm voteSubmitForm);
    //删除投票活动，假删
    public void deleteVote(Integer voteId);
    //根据用户ID查询该用户发表的投票
    public List<VoteList> queryVoteByUserId(Integer userId);
    //发布通知
    public Result addVoteTable(VoteList voteList);

}
