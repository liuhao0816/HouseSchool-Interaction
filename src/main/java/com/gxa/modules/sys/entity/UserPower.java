package com.gxa.modules.sys.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@ApiModel("用户角色权限信息")
public class UserPower {
    @ApiModelProperty(value = "用户ID",name = "userId")
    private int userId;

    @ApiModelProperty(value = "用户名称",name = "userName")
    private String userName;

    @ApiModelProperty(value = "用户头像",name = "userAvatar")
    private String userAvatar;

    @ApiModelProperty(value = "用户角色",name = "roles")
    private Set<Role> roles;

    @ApiModelProperty(value = "学生",name = "students")
    private Set<Student> students;
}
