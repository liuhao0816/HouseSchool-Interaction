package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("班级实体")
public class StudentClass {
    @ApiModelProperty(value = "年级ID",name = "classId")
    private Integer classId;
    @ApiModelProperty(value = "年级名称",name = "classGradeName")
    private String classGradeName;
}
