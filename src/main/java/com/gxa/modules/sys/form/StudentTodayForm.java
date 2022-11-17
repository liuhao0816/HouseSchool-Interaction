package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "今日点名分页")
@Data
public class StudentTodayForm {
    @ApiModelProperty(value = "当前页数",name = "page")
    private Integer page;
    @ApiModelProperty(value = "每页记录数",name = "limit")
    private Integer limit;
    @ApiModelProperty(value = "当前登录用户ID",name = "UserId")
    private Integer UserId;
    @ApiModelProperty(value = "今日时间",name = "todayTime")
    private Date todayTime;
}
