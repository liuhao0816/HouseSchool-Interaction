package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("出勤统计参数实体")
public class StudentAttStatistics {
    @ApiModelProperty(value = "当前页数",name = "page")
    private Integer page;
    @ApiModelProperty(value = "每页记录数",name = "limit")
    private Integer limit;
    @ApiModelProperty(value = "当前登录用户ID",name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "班级名称",name = "className")
    private String className;
    @ApiModelProperty(value = "考勤日期",name = "statusTime")
    private Date statusTime;
}
