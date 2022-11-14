package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel(value = "家长")
public class Teacher {

    @ApiModelProperty(value = "家长信息",name = "user")
    private int teacherJobno;

    @ApiModelProperty(value = "老师用户信息",name = "user")
    private User user;

    @ApiModelProperty(value = "任教科目",name = "course")
    private String course;
}
