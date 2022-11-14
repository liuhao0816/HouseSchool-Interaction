package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.Teacher;

import java.util.Map;


public interface TeacherMapper extends BaseMapper<Teacher> {

    public IPage<Teacher> queryTeacherByPage(Page<Teacher> page, Map<String, Object> params);
}
