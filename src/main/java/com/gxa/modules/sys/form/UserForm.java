package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录信息")
public class UserForm {

    @ApiModelProperty(value = "用户账号",name = "userId")
    private int userId;

    @ApiModelProperty(value = "用户密码",name = "password")
    private String password;

    @ApiModelProperty(value = "验证码",name = "captch")
    private String captch;

    @ApiModelProperty(value = "验证码编号",name = "uuid")
    private String uuid;//用于找验证码的

}
