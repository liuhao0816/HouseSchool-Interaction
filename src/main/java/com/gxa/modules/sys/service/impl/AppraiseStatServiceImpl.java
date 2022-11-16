package com.gxa.modules.sys.service.impl;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 14:21.
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.AppraiseStat;
import com.gxa.modules.sys.mapper.AppraiseStatMapper;
import com.gxa.modules.sys.service.AppraiseStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */

@Service("AppraiseStatService")
@Transactional(rollbackFor = Throwable.class)

public class AppraiseStatServiceImpl extends ServiceImpl<AppraiseStatMapper, AppraiseStat> implements AppraiseStatService {

    @Override
    public List selectAll() {
        List<AppraiseStat> list = this.list();
        return list;

    }
}
