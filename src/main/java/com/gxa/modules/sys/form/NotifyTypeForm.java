package com.gxa.modules.sys.form;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "通知类型响应")
public class NotifyTypeForm {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "通知类型")
    private String type;
    @ApiModelProperty(value = "属性")
    private String attribute;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "是否删除  1 不删除、2删除")
    private Integer isDelete;
}
