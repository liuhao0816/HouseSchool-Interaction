package com.gxa.modules.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("小程序出勤统计")
public class StudentYueAttDay {
    @ApiModelProperty(value = "学生ID",name = "studentId")
    private Integer studentId;
    @ApiModelProperty(value = "学生姓名",name = "studentName")
    private String studentName;
    @ApiModelProperty(value = "出勤天数",name = "attDays")
    private Integer attDays;
    @ApiModelProperty(value = "缺勤天数",name = "absentDays")
    private Integer absentDays;
}
