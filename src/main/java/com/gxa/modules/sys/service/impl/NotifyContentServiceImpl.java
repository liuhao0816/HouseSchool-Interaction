package com.gxa.modules.sys.service.impl;



/**
 * 通知类型
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.NOtifyContent;
import com.gxa.modules.sys.mapper.NotifyContentMapper;
import com.gxa.modules.sys.service.NotifyContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("NotifyContentService")
@Transactional(rollbackFor = Throwable.class)
public class NotifyContentServiceImpl extends ServiceImpl<NotifyContentMapper, NOtifyContent> implements NotifyContentService {


    @Override
    public List selectAll() {
        List<NOtifyContent> list = this.list();
        return list;
    }

}



