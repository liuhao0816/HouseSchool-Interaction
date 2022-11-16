package com.gxa.modules.sys.service.impl;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 15:07.
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.dto.NOtifyContent;
import com.gxa.modules.sys.entity.dto.VoteStat;
import com.gxa.modules.sys.mapper.VoteStatMapper;
import com.gxa.modules.sys.service.VoteStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
@Service("VoteStatService")
@Transactional(rollbackFor = Throwable.class)
public class VoteStatServiceImpl extends ServiceImpl<VoteStatMapper, VoteStat> implements VoteStatService {
    @Override
    public List selectAll() {
        List<VoteStat> list = this.list();
        return list;
    }
}
