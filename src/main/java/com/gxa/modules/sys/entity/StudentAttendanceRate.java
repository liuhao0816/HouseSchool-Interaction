package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("出勤统计实体")
public class StudentAttendanceRate {
    @ApiModelProperty(value = "年级",name = "studentClass")
    private String studentClass;
    @ApiModelProperty(value = "应到人数",name = "dueStudents")
    private Integer dueStudents;
    @ApiModelProperty(value = "实到人数",name = "practicalStudents")
    private Integer practicalStudents;
    @ApiModelProperty(value = "迟到人数",name = "belateStudents")
    private Integer belateStudents;
    @ApiModelProperty(value = "缺勤人数",name = "actualArrivalStudents")
    private Integer actualArrivalStudents;
    @ApiModelProperty(value = "出勤率",name = "attendanceRate")
    private String attendanceRate;
}
