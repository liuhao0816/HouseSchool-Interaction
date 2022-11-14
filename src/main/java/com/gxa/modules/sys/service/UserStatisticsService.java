package com.gxa.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.gxa.modules.sys.entity.UserStatistics;

import java.util.Map;


public interface UserStatisticsService{

  /**
     * 用户统计
     * @param params
     * @return
     */
    PageInfo<UserStatistics> userStatisticsList(Map<String,Object> params);


}
