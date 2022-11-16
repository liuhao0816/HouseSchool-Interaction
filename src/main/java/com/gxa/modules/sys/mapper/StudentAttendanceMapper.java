package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;

import java.util.List;
import java.util.Map;

public interface StudentAttendanceMapper extends BaseMapper<StudentAttendance> {
    //根据老师id查询出老师所在学校id
    public Integer querySchoolIdByUserId(Integer userId);
    //根据学校的id查询出该学校所有年级班级
    public List<StudentClass> queryClassBySchoolId(Integer schoolId);
    public IPage<StudentAttendance> queryAllStudentBy(Page<StudentAttendance> page, Map<String,Object> map);
}
