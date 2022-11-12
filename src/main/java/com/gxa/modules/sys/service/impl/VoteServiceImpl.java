package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.VoteList;
import com.gxa.modules.sys.form.VoteForm;
import com.gxa.modules.sys.mapper.VoteMapper;
import com.gxa.modules.sys.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
@Service
@Slf4j
public class VoteServiceImpl extends ServiceImpl<VoteMapper,VoteList> implements VoteService {

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
    public List<VoteList> queryAllVote(VoteForm voteForm) {
        List<VoteList> voteLists = baseMapper.queryAllVote(voteForm);
        return voteLists;
    }
}
