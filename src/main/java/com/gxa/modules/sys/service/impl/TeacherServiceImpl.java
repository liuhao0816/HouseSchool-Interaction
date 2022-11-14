package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.Teacher;
import com.gxa.modules.sys.mapper.TeacherMapper;
import com.gxa.modules.sys.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public PageUtils queryTeacherByPage(Map<String, Object> params) {

        log.info("---接收到的参数：{}",params);

        Page<Teacher> page = new Page<Teacher>(Long.parseLong((String)params.get("page")), Long.parseLong((String)params.get("limit")));

        IPage<Teacher> teacherIPage = baseMapper.queryTeacherByPage(page, params);

        PageUtils pageUtils = new PageUtils(teacherIPage);
        return pageUtils;
    }


}
