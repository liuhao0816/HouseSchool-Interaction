package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.mapper.UserMapper;
import com.gxa.modules.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



    @Override
    public User queryByUsername(String username) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        return user;

    }

    @Override
    public PageUtils queryByPage(Map<String, Object> params) {

        Page<User> page = new Page<User>(Long.parseLong((String)params.get("page")), Long.parseLong((String)params.get("limit")));

        IPage<User> userIPage = baseMapper.queryByPage(page, params);

        PageUtils pageUtils = new PageUtils(userIPage);
        return pageUtils;
    }

    @Override
    public PageUtils userStatisticsList(Map<String, Object> params) {
        return null;
    }

}
