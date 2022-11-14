package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("V-角色实体")
public class Role {
    @ApiModelProperty(value = "角色ID",name = "roleId")
    private int roleId;
    @ApiModelProperty(value = "角色名字",name = "roleName")
    private String roleName;
}
