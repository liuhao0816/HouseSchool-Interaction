package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.StudentParent;
import com.gxa.modules.sys.entity.Teacher;

import java.util.Map;


public interface StudentParentMapper extends BaseMapper<StudentParent> {

    public IPage<StudentParent> queryStudentParentByPage(Page<StudentParent> page, Map<String, Object> params);
}
