package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户角色")
public class UserRoleForm {

    @ApiModelProperty(value = "用户账号",name = "userId")
    private int userId;

    @ApiModelProperty(value = "角色ID",name = "roleId")
    private int roleId;
}
