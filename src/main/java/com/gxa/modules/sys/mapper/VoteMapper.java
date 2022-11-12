package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;

import java.util.List;

public interface VoteMapper extends BaseMapper<VoteList> {
    public List<VoteList> queryNo();
    public List<VoteList> queryAllVote(VoteForm voteForm);
}
