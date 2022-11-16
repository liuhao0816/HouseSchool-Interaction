package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("V-今日点名学生列表实体")
public class StudentAttendance {
    @ApiModelProperty(value = "学籍号",name = "studentId")
    private Integer studentId;
    @ApiModelProperty(value = "年级班级",name = "studentClass")
    private String studentClass;
    @ApiModelProperty(value = "学生姓名",name = "studentName")
    private String studentName;
    @ApiModelProperty(value = "性别  1:男/2:女",name = "studentSex")
    private Integer studentSex;
    @ApiModelProperty(value = "今日考勤状态  正常/迟到/缺勤",name = "studengAttendanceStatus")
    private String studengAttendanceStatus;
    @ApiModelProperty(value = "电话号码",name = "phoneNumber")
    private String phoneNumber;

    @ApiModelProperty(value = "应到",name = "dueStudents")
    private Integer dueStudents;
    @ApiModelProperty(value = "实到",name = "practicalStudents")
    private Integer practicalStudents;
}
