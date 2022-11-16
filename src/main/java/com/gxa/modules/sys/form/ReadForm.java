package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "已读人员信息")
public class ReadForm {

    @ApiModelProperty(value = "姓名")
    private String studentName;
    @ApiModelProperty(value = "班级")
    private String classGradeName;

    @ApiModelProperty(value = "通知id")
    private Integer contentId;
}
