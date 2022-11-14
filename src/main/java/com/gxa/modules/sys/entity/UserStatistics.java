package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户统计信息")
public class UserStatistics {

    @ApiModelProperty(value = "学校地址",name = "schoolAddress")
    private String schoolAddress;

    @ApiModelProperty(value = "学校名称",name = "schoolName")
    private String schoolName;

    @ApiModelProperty(value = "老师数量",name = "teachers")
    private int teachers;

    @ApiModelProperty(value = "学生数量",name = "students")
    private int students;

    @ApiModelProperty(value = "家长数量",name = "parents")
    private int parents;
}
