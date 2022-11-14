package com.gxa.modules.sys.mapper;


import com.gxa.modules.sys.entity.UserStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserStatisticsMapper{

    public List<UserStatistics> userStatisticsList(Map<String, Object> params);

}
