package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentAttendanceRate;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.service.StudentAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "V-出勤确认接口")
@RestController
@Slf4j
public class StudentAttendanceController {

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    @GetMapping(value = "/sys/studentAttendance/class")
    @ApiOperation(value = "出勤确认-今日点名考勤班级下拉框")
    public Result<List<StudentClass>> queryAllClass(@RequestParam("userId") Integer userId){
        Result<List<StudentClass>> result = new Result<List<StudentClass>>().error();
        try {
            List<StudentClass> studentClasses = this.studentAttendanceService.queryStudentClassByUserId(userId);
            result = new Result<List<StudentClass>>().ok(studentClasses);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "userId",value ="当前登录用户ID",dataType ="int")
    })
    @PostMapping(value = "/sys/studentAttendance/list")
    @ApiOperation(value = "出勤确认-今日点名列表接口")
    public Result<List<StudentAttendance>> queryAllStudentAttendance(@RequestParam @ApiIgnore Map<String,Object> map){
        List<StudentAttendance> studentAttendances = new ArrayList<>();

        StudentAttendance studentAttendance1 = new StudentAttendance();
        studentAttendance1.setStudentId(6001);
        studentAttendance1.setStudentClass("一年级一班");
        studentAttendance1.setStudentName("张三");
        studentAttendance1.setStudentSex(1);
        studentAttendance1.setPhoneNumber("11111111");
        studentAttendance1.setStudengAttendanceStatus("正常");

        StudentAttendance studentAttendance2 = new StudentAttendance();
        studentAttendance2.setStudentId(6002);
        studentAttendance2.setStudentClass("一年级一班");
        studentAttendance2.setStudentName("李四");
        studentAttendance2.setStudentSex(2);
        studentAttendance2.setPhoneNumber("22222222");
        studentAttendance2.setStudengAttendanceStatus("迟到");

        studentAttendances.add(studentAttendance1);
        studentAttendances.add(studentAttendance2);
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances);
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "userId",value ="当前登录用户ID",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "attendenceTime",value ="考勤日期",dataType ="Date"),
            @ApiImplicitParam(paramType = "query",name = "classId",value ="考勤班级ID",dataType ="int")
    })
    @ApiOperation(value = "出勤确认-今日点名开始考勤接口")
    @PostMapping(value = "/sys/studentAttendance/start")
    public Result<List<StudentAttendance>> startAttendance(@RequestParam @ApiIgnore Map<String,Object> map){
        List<StudentAttendance> studentAttendances = new ArrayList<>();

        StudentAttendance studentAttendance1 = new StudentAttendance();
        studentAttendance1.setStudentId(6001);
        studentAttendance1.setStudentClass("一年级一班");
        studentAttendance1.setStudentName("张三");
        studentAttendance1.setStudentSex(1);
        studentAttendance1.setPhoneNumber("11111111");
        studentAttendance1.setStudengAttendanceStatus("正常");

        StudentAttendance studentAttendance2 = new StudentAttendance();
        studentAttendance2.setStudentId(6002);
        studentAttendance2.setStudentClass("一年级一班");
        studentAttendance2.setStudentName("李四");
        studentAttendance2.setStudentSex(2);
        studentAttendance2.setPhoneNumber("22222222");
        studentAttendance2.setStudengAttendanceStatus("迟到");

        studentAttendances.add(studentAttendance1);
        studentAttendances.add(studentAttendance2);
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances);
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "studentId",value ="学籍号",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "studengAttendanceStatus",value ="考勤状态",dataType ="String")
    })
    @PostMapping(value = "/sys/studentAttendance/mark")
    @ApiOperation(value = "出勤确认-今日点名标记考勤状态接口")
    public Result<StudentAttendance> markAttendance(@RequestParam @ApiIgnore Map<String,Object> map){
        String str = (String) map.get("studentId");
        Integer studentId = Integer.valueOf(str);
        String studengAttendanceStatus = (String) map.get("studengAttendanceStatus");
        StudentAttendance studentAttendance1 = new StudentAttendance();
        studentAttendance1.setStudentId(studentId);
        studentAttendance1.setStudentClass("一年级一班");
        studentAttendance1.setStudentName("张三");
        studentAttendance1.setStudentSex(1);
        studentAttendance1.setPhoneNumber("11111111");
        studentAttendance1.setStudengAttendanceStatus(studengAttendanceStatus);
        studentAttendance1.setDueStudents(2);
        studentAttendance1.setPracticalStudents(2);

        Result<StudentAttendance> result = new Result<StudentAttendance>().ok(studentAttendance1);
        return result;
    }

    @ApiOperation(value = "出勤确认-今日点名提交考勤接口")
    @GetMapping(value = "/sys/studentAttendance/submit")
    public Result<List<StudentAttendance>> submitAttendance(Integer userId){

        List<StudentAttendance> studentAttendances = new ArrayList<>();

        StudentAttendance studentAttendance1 = new StudentAttendance();
        studentAttendance1.setStudentId(6001);
        studentAttendance1.setStudentClass("一年级一班");
        studentAttendance1.setStudentName("张三");
        studentAttendance1.setStudentSex(1);
        studentAttendance1.setPhoneNumber("11111111");
        studentAttendance1.setStudengAttendanceStatus("正常");

        StudentAttendance studentAttendance2 = new StudentAttendance();
        studentAttendance2.setStudentId(6002);
        studentAttendance2.setStudentClass("一年级一班");
        studentAttendance2.setStudentName("李四");
        studentAttendance2.setStudentSex(2);
        studentAttendance2.setPhoneNumber("22222222");
        studentAttendance2.setStudengAttendanceStatus("迟到");

        studentAttendances.add(studentAttendance1);
        studentAttendances.add(studentAttendance2);
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances);
        return result;
    }



    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "userId",value ="当前登录用户ID",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "schoolName",value ="学校名称",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "studentClass",value ="年级",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "attendenceTime",value ="考勤日期",dataType ="Date")
    })
    @GetMapping(value = "/sys/attendanceRate/list")
    @ApiOperation(value = "出勤确认-出勤统计列表接口")
    public Result<List<StudentAttendanceRate>> attendanceRate(@RequestParam @ApiIgnore Map<String,Object> map){
        return null;
    }

}
