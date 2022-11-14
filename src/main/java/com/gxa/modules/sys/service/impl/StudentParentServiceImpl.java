package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.StudentParent;
import com.gxa.modules.sys.entity.Teacher;
import com.gxa.modules.sys.mapper.StudentParentMapper;
import com.gxa.modules.sys.mapper.TeacherMapper;
import com.gxa.modules.sys.service.StudentParentService;
import com.gxa.modules.sys.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StudentParentServiceImpl extends ServiceImpl<StudentParentMapper, StudentParent> implements StudentParentService {

    @Override
    public PageUtils queryStudentParentByPage(Map<String, Object> params) {

        log.info("---接收到的参数：{}",params);

        Page<StudentParent> page = new Page<StudentParent>(Long.parseLong((String)params.get("page")), Long.parseLong((String)params.get("limit")));

        IPage<StudentParent> studentParentIPage = baseMapper.queryStudentParentByPage(page, params);

        log.info("---查询数据---{}",studentParentIPage);

        PageUtils pageUtils = new PageUtils(studentParentIPage);
        return pageUtils;
    }


}
