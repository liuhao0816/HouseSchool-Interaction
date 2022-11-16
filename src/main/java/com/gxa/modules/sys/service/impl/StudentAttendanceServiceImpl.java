package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.StudentAttInitializeDto;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.form.StudentStartAttFrom;
import com.gxa.modules.sys.form.StudentTodayForm;
import com.gxa.modules.sys.mapper.StudentAttendanceMapper;
import com.gxa.modules.sys.service.StudentAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    public Result<List<StudentAttendance>> queryAllStudentBy(StudentTodayForm studentTodayFrom) {
        Integer page = studentTodayFrom.getPage();
        Integer limit = studentTodayFrom.getLimit();

        PageHelper.startPage(page,limit);
        List<StudentAttendance> studentAttendances = this.baseMapper.queryAllStudentBy(studentTodayFrom);
        for (int i = 0;i < studentAttendances.size();i++){
            StudentAttendance studentAttendance = studentAttendances.get(i);
            studentAttendance.setStudengAttendanceStatus("正常");
        }
        PageInfo<StudentAttendance> pageInfo = new PageInfo<>(studentAttendances);
        long total = pageInfo.getTotal();

        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances,total);
        return result;
    }

//    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<List<StudentAttendance>> queryMeStudentBy(StudentStartAttFrom studentStartAttFrom) {

        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().error();
        Integer page = studentStartAttFrom.getPage();
        Integer limit = studentStartAttFrom.getLimit();
        Integer userId = studentStartAttFrom.getUserId();
        Integer classId = studentStartAttFrom.getClassId();
        Date todayTime = studentStartAttFrom.getTodayTime();
        System.out.println("------------------todayTime---" + todayTime);

        StudentAttInitializeDto studentAttInitializeDto = new StudentAttInitializeDto();
        studentAttInitializeDto.setAttStatus("正常");
        studentAttInitializeDto.setTodayTime(todayTime);

            //获取该班所有学生的id
            List<Integer> studentIds = this.baseMapper.queryAllStudentId(userId, classId);
            for(int i = 0;i < studentIds.size();i++){
                Integer studentId = studentIds.get(i);
                studentAttInitializeDto.setStudentId(studentId);
                this.baseMapper.insertAllStudentStatus(studentAttInitializeDto);
            }

            PageHelper.startPage(page,limit);
            List<StudentAttendance> studentAttendances = this.baseMapper.queryMeStudentBy(studentStartAttFrom);

            PageInfo<StudentAttendance> pageInfo = new PageInfo<>(studentAttendances);
            long total = pageInfo.getTotal();

            result = new Result<List<StudentAttendance>>().ok(studentAttendances,total);

        return result;
    }
}
