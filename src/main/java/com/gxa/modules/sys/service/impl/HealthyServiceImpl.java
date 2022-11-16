package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Query;
import com.gxa.modules.sys.entity.ClassGrade;
import com.gxa.modules.sys.entity.Healthy;
import com.gxa.modules.sys.entity.dto.HealthyDto;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.mapper.HealthyMapper;
import com.gxa.modules.sys.service.HealthyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HealthyServiceImpl extends ServiceImpl<HealthyMapper, Healthy> implements HealthyService {
    @Override
    public List<Healthy> queryAll() {
        List<Healthy> healthyList = baseMapper.selectList(null);
        return healthyList;
    }

    @Override
    public List<Healthy> queryByPublisher(String userName, String createTime) {
        List<Healthy> healthyList = this.baseMapper.queryByPublisher01(userName,createTime);
        return healthyList;
    }

    @Override
    public void add(Healthy healthy) {
        baseMapper.insert(healthy);
    }

    @Override
    public void delete(String userName, String createTime) {
        this.baseMapper.queryByPublisherTime(userName,createTime);
    }

    @Override
    public List<Healthy> queryByHealthyDto(String firstDateTime, String lastDateTime, HealthyDto healthyDto) {
        String studentName = healthyDto.getStudentName();
        String gradeClass = healthyDto.getGradeClass();
        List<Healthy> healthyList = this.baseMapper.queryByHealthyDto01(firstDateTime,lastDateTime,studentName,gradeClass);
        return healthyList;
    }

    @Override
    public PageUtils queryByPage01(Map<String, Object> params) {
        IPage<Healthy> page = this.page(new Query<Healthy>().getPage(params));
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

    @Override
    public List<Healthy> queryByHealthyDtos(HealthyDto healthyDto) {
        String studentName = healthyDto.getStudentName();
        String gradeClass = healthyDto.getGradeClass();
        List<Healthy> healthyList = this.baseMapper.queryByHealthyDto02(studentName,gradeClass);
        return healthyList;
    }

    @Override
    public List<ClassGrade> queryByHealthyClass(int userId) {
        List<ClassGrade> healthyClass =  this.baseMapper.queryByHealthyClass(userId);
        return healthyClass;
    }

    @Override
    public List<User> queryByHealthyTeacher(int classId) {
        List<User> healthyTeacher = this.baseMapper.queryByHealthyTeacher(classId);
        return healthyTeacher;
    }
}
