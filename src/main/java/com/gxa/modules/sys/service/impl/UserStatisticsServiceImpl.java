package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserStatistics;
import com.gxa.modules.sys.mapper.UserStatisticsMapper;
import com.gxa.modules.sys.service.UserStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Autowired
    private UserStatisticsMapper userStatisticsMapper;
    @Override
    public PageInfo<UserStatistics> userStatisticsList(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt((String) params.get("page")), Integer.parseInt((String) params.get("limit")));
        List<UserStatistics> userStatistics = userStatisticsMapper.userStatisticsList(params);
        PageInfo<UserStatistics> pageInfo = new PageInfo<>(userStatistics);
        return pageInfo;
    }

}
