package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.mapper.StudentMapper;
import com.gxa.modules.sys.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public PageUtils queryStutentByPage(Map<String, Object> params) {

        log.info("---接收到的参数：{}",params);

        Page<Student> page = new Page<Student>(Long.parseLong((String)params.get("page")), Long.parseLong((String)params.get("limit")));

        IPage<Student> studentIPage = baseMapper.queryStutentByPage(page, params);

        PageUtils pageUtils = new PageUtils(studentIPage);
        return pageUtils;
    }


}
