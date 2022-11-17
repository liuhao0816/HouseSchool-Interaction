package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("开始考勤参数实体")
public class StudentStartAttFrom {
    @ApiModelProperty(value = "当前页数",name = "page")
    private Integer page;
    @ApiModelProperty(value = "每页记录数",name = "limit")
    private Integer limit;
    @ApiModelProperty(value = "当前登录用户ID",name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "当前时间",name = "todatTime")
    private Date todayTime;
    @ApiModelProperty(value = "班级ID",name = "classId")
    private Integer classId;

}
