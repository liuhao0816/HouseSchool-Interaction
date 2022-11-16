package com.gxa.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.gxa.modules.sys.entity.UserPower;
import com.gxa.modules.sys.entity.UserStatistics;
import com.gxa.modules.sys.form.UserRoleForm;

import java.util.Map;


public interface UserStatisticsService{

  /**
     * 用户统计
     * @param params
     * @return
     */
    PageInfo<UserStatistics> userStatisticsList(Map<String,Object> params);

  /**
   * 根据userId查询用户角色和权限
   * @param userId
   * @return
   */
  UserPower userPowerByUserId(int userId);

  /**
   * 根据userId和roleId
   * @param userRoleForm
   * @return
   */
  UserPower userPowerByUserIdAndRoleId(UserRoleForm userRoleForm);


}
