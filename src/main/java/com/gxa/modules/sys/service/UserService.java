package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.User;

import java.util.Map;


public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User queryByUsername(String username);

    /**
     * 用户带条件分页查询
     * @param params
     * @return
     */
    PageUtils queryByPage(Map<String,Object> params);

  /**
     * 用户统计
     * @param params
     * @return
     */
    PageUtils userStatisticsList(Map<String,Object> params);


}
