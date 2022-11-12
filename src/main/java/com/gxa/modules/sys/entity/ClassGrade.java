package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "班级")
public class ClassGrade {
    @ApiModelProperty(value = "班级编号",name = "classId")
    private int classId;

    @ApiModelProperty(value = "班级名称",name = "classGradeName")
    private String classGradeName;

    @ApiModelProperty(value = "班主任",name = "teacher")
    private Object teacher;

    @ApiModelProperty(value = "所属学校",name = "school")
    private School school;

    @ApiModelProperty(value = "备注",name = "remarks")
    private String remarks;
}
