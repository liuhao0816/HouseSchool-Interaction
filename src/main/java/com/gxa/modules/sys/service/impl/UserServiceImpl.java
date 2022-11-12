package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
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
    public User queryByUsername01(String username) {
        User user = baseMapper.queryByUsername01(username);
        return user;
    }

    @Override
    public PageUtils queryByPage(Map<String, Object> params) {
        List<User> users = baseMapper.queryByPage(params);

        log.info("---查询用户：   {}",users);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        IPage<User> page = this.page(new Query<User>().getPage(params));

        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public PageUtils queryByPage01(Map<String, Object> params) {
        IPage<User> page = this.page(new Query<User>().getPage(params));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public PageUtils queryByPage02(Map<String, Object> params) {
        String username = (String)params.get("user_name");
        String id = (String)params.get("id");


        IPage<User> page = this.page(new Query<User>().getPage(params),
                new QueryWrapper<User>().like(StringUtils.isNotEmpty(username),"user_name",username)
                        .eq(StringUtils.isNotEmpty(id),"id",id)
                );
        return new PageUtils(page);
    }



}
