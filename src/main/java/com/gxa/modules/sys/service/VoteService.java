package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;

import java.util.List;

public interface VoteService extends IService<VoteList> {
    //测试无条件查询投票列表
    public List<VoteList> queryNo();
    //条件查询投票列表
    public List<VoteList> queryAllVote(VoteForm voteForm);
}
