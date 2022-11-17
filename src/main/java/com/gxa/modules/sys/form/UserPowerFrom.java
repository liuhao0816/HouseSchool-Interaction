package com.gxa.modules.sys.form;

import com.gxa.modules.sys.entity.Role;
import com.gxa.modules.sys.entity.Student;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel("用户角色权限信息")
public class UserPowerFrom {
    @ApiModelProperty(value = "用户ID",name = "userId")
    private int userId;

    @ApiModelProperty(value = "用户名称",name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户头像",name = "userAvatar")
    private String userAvatar;

    @ApiModelProperty(value = "用户当前角色",name = "roleFrom")
    private RoleFrom roleFrom;

    @ApiModelProperty(value = "用户当前角色token",name = "token")
    private String token;
}
