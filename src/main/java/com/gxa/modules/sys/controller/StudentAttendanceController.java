package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.Student;
import com.gxa.modules.sys.entity.StudentAttendance;
import com.gxa.modules.sys.entity.StudentAttendanceRate;
import com.gxa.modules.sys.entity.StudentClass;
import com.gxa.modules.sys.form.StudentAttStatistics;
import com.gxa.modules.sys.form.StudentAttStatusForm;
import com.gxa.modules.sys.form.StudentStartAttFrom;
import com.gxa.modules.sys.form.StudentTodayForm;
import com.gxa.modules.sys.service.StudentAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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


    @PostMapping(value = "/sys/studentAttendance/list")
    @ApiOperation(value = "出勤确认-今日点名列表接口")
    public Result<List<StudentAttendance>> queryAllStudentAttendance(@RequestBody StudentTodayForm studentTodayFrom){

        Result<List<StudentAttendance>> result = new Result<List<StudentAttendance>>().error();

        try {
            result = this.studentAttendanceService.queryAllStudentBy(studentTodayFrom);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "出勤确认-今日点名开始考勤接口")
    @PostMapping(value = "/sys/studentAttendance/start")
    public Result<List<StudentAttendance>> startAttendance(@RequestBody StudentStartAttFrom studentStartAttFrom){

        Result<List<StudentAttendance>> result = this.studentAttendanceService.queryMeStudentBy(studentStartAttFrom);
        return result;
    }

    @PostMapping(value = "/sys/studentAttendance/mark")
    @ApiOperation(value = "出勤确认-今日点名标记考勤状态接口")
    public Result markAttendance(@RequestBody StudentAttStatusForm studentAttStatusForm){

        Result result = this.studentAttendanceService.updateStudentStatus(studentAttStatusForm);

        return result;
    }


    @PostMapping(value = "/sys/attendanceRate/list")
    @ApiOperation(value = "出勤确认-出勤统计列表接口")
    public Result<List<StudentAttendanceRate>> attendanceRate(@RequestBody StudentAttStatistics studentAttStatistics){
        Result<List<StudentAttendanceRate>> result = new Result<List<StudentAttendanceRate>>().error();
        Map<String,Object> map = new HashMap<>();

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String time1 = format;
        String time2 = "2022-11-16";

        List<StudentAttendanceRate> studentAttendanceRates1 = new ArrayList<>();
        StudentAttendanceRate studentAttendanceRate1 = new StudentAttendanceRate();
        studentAttendanceRate1.setStudentClass("一年级");
        studentAttendanceRate1.setDueStudents(20);
        studentAttendanceRate1.setPracticalStudents(18);
        studentAttendanceRate1.setBelateStudents(1);
        studentAttendanceRate1.setActualArrivalStudents(2);
        //求百分比
        NumberFormat numberFormat = NumberFormat.getInstance();
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format1 = numberFormat.format((float) 18 / (float) 20 * 100);
        studentAttendanceRate1.setAttendanceRate(format1+"%");

        StudentAttendanceRate studentAttendanceRate2 = new StudentAttendanceRate();
        studentAttendanceRate2.setStudentClass("二年级");
        studentAttendanceRate2.setDueStudents(23);
        studentAttendanceRate2.setPracticalStudents(20);
        studentAttendanceRate2.setBelateStudents(1);
        studentAttendanceRate2.setActualArrivalStudents(3);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format2 = numberFormat.format((float) 20 / (float) 23 * 100);
        studentAttendanceRate2.setAttendanceRate(format2+"%");

        StudentAttendanceRate studentAttendanceRate3 = new StudentAttendanceRate();
        studentAttendanceRate3.setStudentClass("三年级");
        studentAttendanceRate3.setDueStudents(22);
        studentAttendanceRate3.setPracticalStudents(20);
        studentAttendanceRate3.setBelateStudents(1);
        studentAttendanceRate3.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format3 = numberFormat.format((float) 20 / (float) 22 * 100);
        studentAttendanceRate3.setAttendanceRate(format3+"%");

        StudentAttendanceRate studentAttendanceRate4 = new StudentAttendanceRate();
        studentAttendanceRate4.setStudentClass("四年级");
        studentAttendanceRate4.setDueStudents(25);
        studentAttendanceRate4.setPracticalStudents(23);
        studentAttendanceRate4.setBelateStudents(1);
        studentAttendanceRate4.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format4 = numberFormat.format((float) 23 / (float) 25 * 100);
        studentAttendanceRate4.setAttendanceRate(format4+"%");

        StudentAttendanceRate studentAttendanceRate5 = new StudentAttendanceRate();
        studentAttendanceRate5.setStudentClass("五年级");
        studentAttendanceRate5.setDueStudents(24);
        studentAttendanceRate5.setPracticalStudents(23);
        studentAttendanceRate5.setBelateStudents(1);
        studentAttendanceRate5.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format5 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate5.setAttendanceRate(format5+"%");

        StudentAttendanceRate studentAttendanceRate6 = new StudentAttendanceRate();
        studentAttendanceRate6.setStudentClass("六年级");
        studentAttendanceRate6.setDueStudents(24);
        studentAttendanceRate6.setPracticalStudents(23);
        studentAttendanceRate6.setBelateStudents(1);
        studentAttendanceRate6.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format6 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate6.setAttendanceRate(format6+"%");

        studentAttendanceRates1.add(studentAttendanceRate1);
        studentAttendanceRates1.add(studentAttendanceRate2);
        studentAttendanceRates1.add(studentAttendanceRate3);
        studentAttendanceRates1.add(studentAttendanceRate4);
        studentAttendanceRates1.add(studentAttendanceRate5);
        studentAttendanceRates1.add(studentAttendanceRate6);
        map.put(time1,studentAttendanceRates1);


        List<StudentAttendanceRate> studentAttendanceRates2 = new ArrayList<>();
        StudentAttendanceRate studentAttendanceRate21 = new StudentAttendanceRate();
        studentAttendanceRate21.setStudentClass("一年级");
        studentAttendanceRate21.setDueStudents(20);
        studentAttendanceRate21.setPracticalStudents(18);
        studentAttendanceRate21.setBelateStudents(1);
        studentAttendanceRate21.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format21 = numberFormat.format((float) 18 / (float) 20 * 100);
        studentAttendanceRate21.setAttendanceRate(format21+"%");

        StudentAttendanceRate studentAttendanceRate22 = new StudentAttendanceRate();
        studentAttendanceRate22.setStudentClass("二年级");
        studentAttendanceRate22.setDueStudents(23);
        studentAttendanceRate22.setPracticalStudents(20);
        studentAttendanceRate22.setBelateStudents(1);
        studentAttendanceRate22.setActualArrivalStudents(3);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format22 = numberFormat.format((float) 20 / (float) 23 * 100);
        studentAttendanceRate22.setAttendanceRate(format22+"%");

        StudentAttendanceRate studentAttendanceRate23 = new StudentAttendanceRate();
        studentAttendanceRate23.setStudentClass("三年级");
        studentAttendanceRate23.setDueStudents(22);
        studentAttendanceRate23.setPracticalStudents(20);
        studentAttendanceRate23.setBelateStudents(1);
        studentAttendanceRate23.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format23 = numberFormat.format((float) 20 / (float) 22 * 100);
        studentAttendanceRate23.setAttendanceRate(format23+"%");

        StudentAttendanceRate studentAttendanceRate24 = new StudentAttendanceRate();
        studentAttendanceRate24.setStudentClass("四年级");
        studentAttendanceRate24.setDueStudents(25);
        studentAttendanceRate24.setPracticalStudents(23);
        studentAttendanceRate24.setBelateStudents(1);
        studentAttendanceRate24.setActualArrivalStudents(2);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format24 = numberFormat.format((float) 23 / (float) 25 * 100);
        studentAttendanceRate24.setAttendanceRate(format24+"%");

        StudentAttendanceRate studentAttendanceRate25 = new StudentAttendanceRate();
        studentAttendanceRate25.setStudentClass("五年级");
        studentAttendanceRate25.setDueStudents(24);
        studentAttendanceRate25.setPracticalStudents(23);
        studentAttendanceRate25.setBelateStudents(1);
        studentAttendanceRate25.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format25 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate25.setAttendanceRate(format25+"%");

        StudentAttendanceRate studentAttendanceRate26 = new StudentAttendanceRate();
        studentAttendanceRate26.setStudentClass("六年级");
        studentAttendanceRate26.setDueStudents(24);
        studentAttendanceRate26.setPracticalStudents(23);
        studentAttendanceRate26.setBelateStudents(1);
        studentAttendanceRate26.setActualArrivalStudents(1);
        //小数位最大2位
        numberFormat.setMaximumFractionDigits(2);
        String format26 = numberFormat.format((float) 23 / (float) 24 * 100);
        studentAttendanceRate26.setAttendanceRate(format26+"%");

        studentAttendanceRates2.add(studentAttendanceRate21);
        studentAttendanceRates2.add(studentAttendanceRate22);
        studentAttendanceRates2.add(studentAttendanceRate23);
        studentAttendanceRates2.add(studentAttendanceRate24);
        studentAttendanceRates2.add(studentAttendanceRate25);
        studentAttendanceRates2.add(studentAttendanceRate26);
        map.put(time2,studentAttendanceRates2);

        Integer page = studentAttStatistics.getPage();
        Integer limit = studentAttStatistics.getLimit();
        Date statusTime = studentAttStatistics.getStatusTime();
        String time = simpleDateFormat.format(statusTime);
        String className = studentAttStatistics.getClassName();


        List<StudentAttendanceRate> studentAttendanceRates = (List<StudentAttendanceRate>) map.get(time);
        System.out.println("-------------->>>>>>" + studentAttendanceRates);
//        if(studentAttendanceRates == null){
//            result = new Result<List<StudentAttendanceRate>>().error("未统计该查询时间的出勤率，请查询“2022-11-16”或今天！");
//        }
        if(studentAttendanceRates != null){
            if(!(className == null)){
                for (int i = 0;i < studentAttendanceRates.size();i++){
                    StudentAttendanceRate studentAttendanceRate = studentAttendanceRates.get(i);
                    if(className.equals(studentAttendanceRate.getStudentClass())){
                        List<StudentAttendanceRate> rates = new ArrayList<>();
                        rates.add(studentAttendanceRate);

                        result = new Result<List<StudentAttendanceRate>>().ok(rates,1);
                    }
                }
            }else {
                Integer size = studentAttendanceRates.size();
                Long total = size.longValue();
                result = new Result<List<StudentAttendanceRate>>().ok(studentAttendanceRates,total);
            }
        }else {
            result = new Result<List<StudentAttendanceRate>>().error("未统计该查询时间的出勤率，请查询“2022-11-16”或今天！");
        }
        return result;
    }

    @GetMapping(value = "/app/studentAll/list")
    @ApiOperation(value = "出勤确认-小程序-所有学生信息")
    public Result<List<Student>> queryXAllStudent(@RequestParam("userId") Integer userId,@RequestParam("classId") Integer classId){
        List<Student> students = this.studentAttendanceService.queryXAllStudent(userId, classId);
        Integer size = students.size();
        Long total = size.longValue();
        Result<List<Student>> result = new Result<List<Student>>().ok(students,total);
        return result;
    }
}
