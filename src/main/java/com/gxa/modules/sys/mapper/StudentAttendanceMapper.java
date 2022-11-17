package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.StudentAttInitializeDto;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.form.StudentAttStatusForm;
import com.gxa.modules.sys.form.StudentStartAttFrom;
import com.gxa.modules.sys.form.StudentTodayForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentAttendanceMapper extends BaseMapper<StudentAttendance> {
    //根据老师id查询出老师所在学校id
    public Integer querySchoolIdByUserId(Integer userId);
    //根据学校的id查询出该学校所有年级班级
    public List<StudentClass> queryClassBySchoolId(Integer schoolId);
    public List<StudentAttendance> queryAllStudentBy(StudentTodayForm studentTodayFrom);
    //根据登录用户id和班级id查询该班所有学生的id
    public List<Integer> queryAllStudentId(@Param("userId") Integer userId, @Param("classId") Integer classId);
    //将某班级内所有学生考勤状态初始化
    public void insertAllStudentStatus(StudentAttInitializeDto studentAttInitializeDto);
    //根据条件获取当前该班所有学生初始化考勤状态
    public List<StudentAttendance> queryMeStudentBy(StudentStartAttFrom studentStartAttFrom);
    public void updateStudentStatus(StudentAttStatusForm studentAttStatusForm);
    public List<Student> queryXAllStudent(@Param("userId") Integer userId,@Param("classId") Integer classId);
    public List<Student> queryXAllStudentName(Integer classId);
}
