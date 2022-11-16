package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;

import java.util.List;
import java.util.Map;

public interface StudentAttendanceService extends IService<StudentAttendance> {
    //根据老师id查询该学校所有年级
    public List<StudentClass> queryStudentClassByUserId(Integer userId);
    //根据老师id查询该老师所管班级学生
    public PageUtils queryAllStudentBy(Map<String,Object> map);


}
