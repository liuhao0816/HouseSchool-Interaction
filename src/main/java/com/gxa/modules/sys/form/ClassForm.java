package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "年级列表")
public class ClassForm {
    @ApiModelProperty(value = "班级编号")
    private Integer classId;
    @ApiModelProperty(value = "年级级名称")
    private String classGradeName;
}
