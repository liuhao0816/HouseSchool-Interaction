package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.StudentAttendance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(value = "/sys/studentAttendance/class")
    @ApiOperation(value = "出勤确认-今日点名考勤班级下拉框")
    public Result<Map<Integer,String>> queryAllClass(@RequestParam("userId") Integer userId){
        Map<Integer,String> map = new HashMap<>();
        Integer i1 = 5001;
        Integer i2 = 5002;
        String s1 = "一年级一班";
        String s2 = "一年级二班";

        map.put(i1,s1);
        map.put(i2,s2);

        Result<Map<Integer,String>> result = new Result<Map<Integer,String>>().ok(map);
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "userId",value ="当前登录用户ID",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "attendenceTime",value ="考勤日期",dataType ="Date"),
            @ApiImplicitParam(paramType = "query",name = "classId",value ="考勤班级ID",dataType ="int")
    })
    @GetMapping(value = "/sys/studentAttendance/list")
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
        studentAttendance1.setStudentSex(2);
        studentAttendance1.setPhoneNumber("22222222");
        studentAttendance1.setStudengAttendanceStatus("迟到");

        studentAttendances.add(studentAttendance1);
        studentAttendances.add(studentAttendance2);
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances);
        return result;
    }

    @ApiOperation(value = "出勤确认-今日点名开始考勤接口")
    @GetMapping(value = "/sys/studentAttendance/start")
    public Result<List<StudentAttendance>> startAttendance(@RequestParam("userId") Integer userId){
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
        studentAttendance1.setStudentSex(2);
        studentAttendance1.setPhoneNumber("22222222");
        studentAttendance1.setStudengAttendanceStatus("迟到");

        studentAttendances.add(studentAttendance1);
        studentAttendances.add(studentAttendance2);
        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().ok(studentAttendances);
        return result;
    }

    public Result markAttendance(@RequestParam @ApiIgnore Map<String,Object> map){

        return null;
    }

}
