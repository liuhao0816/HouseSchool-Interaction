package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.Teacher;

import java.util.Map;


public interface TeacherService extends IService<Teacher> {

    /**
     * 学生带条件分页查询
     * @param params
     * @return
     */
    PageUtils queryTeacherByPage(Map<String,Object> params);


}
