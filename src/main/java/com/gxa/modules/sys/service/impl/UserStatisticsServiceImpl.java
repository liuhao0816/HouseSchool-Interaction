package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.*;
import com.gxa.modules.sys.form.UserRoleForm;
import com.gxa.modules.sys.mapper.UserStatisticsMapper;
import com.gxa.modules.sys.service.UserStatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Override
    public UserPower userPowerByUserId(int userId) {

        UserPower userPower = userStatisticsMapper.userPowerByUserId(userId);
        Set<Role> roles = userPower.getRoles();

        UserRoleForm userRoleForm = new UserRoleForm();
        userRoleForm.setUserId(userId);

        for (Role role:roles) {
            userRoleForm.setRoleId(role.getRoleId());
            List<String> strs = userStatisticsMapper.queryClassGradeName(userRoleForm);
            Set<String> sets = new HashSet<>(strs);
            role.setClassGradeNames(sets);

            if (role.getRoleId() == 3){
                Set<Student> students = userStatisticsMapper.queryStudentByUserId(userId);
                userPower.setStudents(students);
            }
        }

        return userPower;
    }

    @Override
    public UserPower userPowerByUserIdAndRoleId(UserRoleForm userRoleForm) {
        userStatisticsMapper.queryClassGradeName(userRoleForm);
        return null;
    }

}
