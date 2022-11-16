package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.*;
import com.gxa.modules.sys.form.VoteForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VoteMapper extends BaseMapper<VoteList> {
    public List<VoteList> queryNo();
    public IPage<VoteList> queryAllVote(Page<VoteList> page,VoteForm voteForm);
    public VoteList queryByVoteId(Integer voteId);
    public Integer countOptionPoll(Integer voteOptionId);

    //根据uderId判断该对象是不是老师或家长
    public Teacher queryByUserId(Integer userId);
    public StudentParent queryByParentUserId(Integer userId);

    //根据用户id查询该用户是否参与过投票
    public Integer queryIsPoll(@Param("voteId") Integer voteId,@Param("userId") Integer userId);
    //添加用户投票数据
    public void addSubmitVote(@Param("voteOptionId") Integer voteOptionId,@Param("userId") Integer userId);
    //根据投票活动id，获取投票参与对象
    public List<Role> queryRole(Integer voteId);
    public void updateVoteIsDelete(Integer voteId);
    public List<VoteList> queryVoteByUserId(Integer userId);
    //根据投票活动的id，查询发起活动人的userId
    public Integer queryUserIdByVoteId(Integer voteId);
    //根据发布者用户id，查询该老师所在班级，查询该班级所有家长的id
    public List<Integer> queryUserIdsByUserId(Integer teacherUserId);
    //获取vote_table表vote_id最大值
    public Integer queryVoteIdMax();

    public void addVoteTable(VoteList voteList);
    public void addVoteOption(@Param("voteOption") String voteOption,@Param("voteId") Integer voteId);
    public void addVoteRole(@Param("voteId") Integer voteId,@Param("roleId") Integer roleId);

}
