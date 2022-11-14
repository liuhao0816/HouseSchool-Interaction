package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.User;

import java.util.Map;


public interface StudentService extends IService<Student> {

    /**
     * 学生带条件分页查询
     * @param params
     * @return
     */
    PageUtils queryStutentByPage(Map<String,Object> params);


}
