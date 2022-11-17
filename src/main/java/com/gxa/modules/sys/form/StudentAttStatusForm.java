package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("修改考勤状态实体")
public class StudentAttStatusForm {
    @ApiModelProperty(value = "学籍号",name = "studentId")
    private Integer studentId;
    @ApiModelProperty(value = "今日考勤状态  正常/迟到/缺勤",name = "studengAttendanceStatus")
    private String studengAttendanceStatus;
    @ApiModelProperty(value = "今日考勤时间",name = "todayTime")
    private Date todayTime;
}
