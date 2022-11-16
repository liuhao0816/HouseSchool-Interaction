package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel("权限")
public class Power {
    @ApiModelProperty(value = "权限ID",name = "powerId")
    private int powerId;

    @ApiModelProperty(value = "权限名称",name = "powerName")
    private String powerName;

    @ApiModelProperty(value = "权限标识",name = "powerMark")
    private String powerMark;
}
