package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.StudentAttInitializeDto;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.form.StudentAttStatusForm;
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
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().error();

        PageHelper.startPage(page,limit);
        List<StudentAttendance> studentAttendances = this.baseMapper.queryAllStudentBy(studentTodayFrom);
        if(studentAttendances.size() > 0){
            int count = 0;
            int size = studentAttendances.size();
            for(int j = 0;j < size;j++){
                StudentAttendance studentAttendance = studentAttendances.get(j);
                studentAttendance.setDueStudents(studentAttendances.size());
                if("缺勤".equals(studentAttendance.getStudengAttendanceStatus())){
                    count++;
                }
            }
            for(int k = 0;k < size;k++){
                StudentAttendance studentAttendance = studentAttendances.get(k);
                studentAttendance.setPracticalStudents(size - count);
            }
            PageInfo<StudentAttendance> pageInfo = new PageInfo<>(studentAttendances);
            long total = pageInfo.getTotal();

            result = new Result<List<StudentAttendance>>().ok(studentAttendances,total);
        }else {
            result = new Result<List<StudentAttendance>>().error(ErrorCode.VERIFICATION_CODE_ERROR,"该班级今日还未考勤，无考勤信息，请开始考勤！");
        }

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

        StudentTodayForm studentTodayForm = new StudentTodayForm();
        studentTodayForm.setUserId(userId);
        studentTodayForm.setTodayTime(todayTime);
        List<StudentAttendance> studentAttendances111 = this.baseMapper.queryAllStudentBy(studentTodayForm);
        if(studentAttendances111.size() > 0){
            result = new Result<List<StudentAttendance>>().error(ErrorCode.CAPTCHA_ERROR,"该班今日已考勤，请勿重复考勤！");
        }else {

            //获取该班所有学生的id
            List<Integer> studentIds = this.baseMapper.queryAllStudentId(userId, classId);

            if(studentIds.size() > 0){
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
            }else {
                result = new Result<List<StudentAttendance>>().error(ErrorCode.UNAUTHORIZED,"您不是该班级的老师，没有权限考勤该班级");
            }
        }
        return result;
    }

    @Override
    public Result updateStudentStatus(StudentAttStatusForm studentAttStatusForm) {
        Result result = new Result<>().error();
        try {
            this.baseMapper.updateStudentStatus(studentAttStatusForm);
            result = new Result<>().ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Student> queryXAllStudent(Integer userId, Integer classId) {
        List<Student> students = this.baseMapper.queryXAllStudent(userId, classId);
        return students;
    }
}
