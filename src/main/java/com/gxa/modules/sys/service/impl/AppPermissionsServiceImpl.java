package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.entity.AppraisePermissions;
import com.gxa.modules.sys.entity.ClassGrade;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.mapper.AppraisePermissionsMapper;
import com.gxa.modules.sys.service.AppPermissionsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppPermissionsServiceImpl extends ServiceImpl<AppraisePermissionsMapper, AppraisePermissions> implements AppPermissionsService {
    @Override
    public void queryByPermissionsLaunch(AppraisePermissions appraisePermissions) {
        UpdateWrapper<AppraisePermissions> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",1);
        baseMapper.update(appraisePermissions,updateWrapper);
    }

    @Override
    public List<ClassGrade> queryByPermissionsHeadteacher(int userId) {
        List<ClassGrade> headteacherClass =  this.baseMapper.queryByHeadteacherClass(userId);
        return headteacherClass;
    }

    @Override
    public AppraisePermissions queryByTeacherType() {
        QueryWrapper<AppraisePermissions> wrapper = new QueryWrapper<>();
        wrapper.eq("id",1);
        AppraisePermissions appraisePermissions= this.baseMapper.selectOne(wrapper);
        return appraisePermissions;
    }

    @Override
    public List<ClassGrade> queryByPermissionsTeacher(int userId) {
        List<ClassGrade> teacherClass =  this.baseMapper.queryByTeacherClass(userId);
        return teacherClass;
    }

    @Override
    public List<ClassGrade> queryByPermissionsappraiseUser(int userId) {
        List<ClassGrade> appraiseUser =  this.baseMapper.queryByAppraiseUser(userId);
        return appraiseUser;
    }

    @Override
    public List<User> queryByAppraiseStudent(int classId) {
        List<User> appraiseStudent = this.baseMapper.queryByAppraiseStudent(classId);
        return appraiseStudent;
    }
}
