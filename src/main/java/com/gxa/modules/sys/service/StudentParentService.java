package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.StudentParent;

import java.util.Map;


public interface StudentParentService extends IService<StudentParent> {

    /**
     * 学生带条件分页查询
     * @param params
     * @return
     */
    PageUtils queryStudentParentByPage(Map<String,Object> params);


}
