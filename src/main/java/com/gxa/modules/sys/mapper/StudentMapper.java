package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.User;

import java.util.Map;


public interface StudentMapper extends BaseMapper<Student> {

    public IPage<Student> queryStutentByPage(Page<Student> page, Map<String, Object> params);
}
