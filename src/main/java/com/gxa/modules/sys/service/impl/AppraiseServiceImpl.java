package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.*;
import com.gxa.modules.sys.mapper.AppraiseMapper;
import com.gxa.modules.sys.service.AppraiseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class AppraiseServiceImpl extends ServiceImpl<AppraiseMapper, Appraise> implements AppraiseService {



    @Override
    public List<Appraise> queryAll() {
        List<Appraise> appraises = baseMapper.selectList(null);
        return appraises;
    }

    @Override
    public void add(Appraise appraise) {
        baseMapper.insert(appraise);
    }

    @Override
    public List<Appraise> queryByPublisher(int id) {
        List<Appraise> appraises = this.baseMapper.queryByPublisher01(id);
        return appraises;
    }

    @Override
    public void update(Appraise appraise) {
        UpdateWrapper<Appraise> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",appraise.getId());
        baseMapper.update(appraise,updateWrapper);
    }

    @Override
    public void delete(int id) {
        this.baseMapper.queryByPublisherTime(id);
    }

    @Override
    public List<Appraise> queryByAppraiseDto(String firstDateTime, String lastDateTime,String studentName,String gradeClass,String appraiseTime ) {

        List<Appraise> appraises = this.baseMapper.queryByAppraiseDto01(firstDateTime,lastDateTime,studentName,gradeClass,appraiseTime);
        return appraises;
    }

    @Override
    public PageUtils queryByPage01(Map<String, Object> params) {
        IPage<Appraise> page = this.page(new Query<Appraise>().getPage(params));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public List<Appraise> queryByAppraiseDtos(String studentName,String gradeClass) {

        List<Appraise> appraises = this.baseMapper.queryByAppraiseDto02(studentName,gradeClass);
        return appraises;
    }

    @Override
    public List<User> queryByPermissionsTeacher() {
        List<User> teacher = this.baseMapper.queryByPermissionsTeacher();
        return teacher;
    }

    @Override
    public List<Appraise> queryAll01(String userName) {
        QueryWrapper<Appraise> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        List<Appraise> appraise= this.baseMapper.selectList(wrapper);
        return appraise;
    }
}
