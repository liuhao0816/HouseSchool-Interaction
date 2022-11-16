package com.gxa.modules.sys.form;

import com.gxa.modules.sys.entity.Power;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel("V-角色实体")
public class RoleFrom {
    @ApiModelProperty(value = "角色ID",name = "roleId")
    private int roleId;

    @ApiModelProperty(value = "角色名字",name = "roleName")
    private String roleName;

    @ApiModelProperty(value = "学校名称",name = "schoolName")
    private String schoolName;

    @ApiModelProperty(value = "角色拥有权限",name = "powers")
    private Set<Power> powers;
}
