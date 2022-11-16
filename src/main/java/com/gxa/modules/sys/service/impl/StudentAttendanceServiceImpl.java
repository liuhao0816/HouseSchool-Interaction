package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.mapper.StudentAttendanceMapper;
import com.gxa.modules.sys.service.StudentAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class StudentAttendanceServiceImpl extends ServiceImpl<StudentAttendanceMapper, StudentAttendance> implements StudentAttendanceService {

    @Override
    public List<StudentClass> queryStudentClassByUserId(Integer userId) {
        Integer schoolId = this.baseMapper.querySchoolIdByUserId(userId);
        List<StudentClass> studentClasses = this.baseMapper.queryClassBySchoolId(schoolId);

        return studentClasses;
    }

    @Override
    public PageUtils queryAllStudentBy(Map<String, Object> map) {
        String p = (String) map.get("page");
        Integer pa = Integer.valueOf(p);
        Long page = pa.longValue();

        String l = (String) map.get("limit");
        Integer li = Integer.valueOf(l);
        Long limit = pa.longValue();

        Page<StudentAttendance> listStudentAttendance = new Page<>(page,limit);
        IPage<StudentAttendance> StudentAttendanceIpage = this.baseMapper.queryAllStudentBy(listStudentAttendance,map);

        PageUtils pageUtils = new PageUtils(StudentAttendanceIpage);
        return pageUtils;
    }
}
