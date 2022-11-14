package com.gxa.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gxa.common.validator.group.AddGroup;
import com.gxa.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@TableName("user")
@ApiModel(value = "用户")
public class User implements Serializable {
    @ApiModelProperty(value = "用户账号",name = "userId")
    private int userId;

    @ApiModelProperty(value = "用户姓名",name = "username")
    private String userName;

    @ApiModelProperty(value = "用户性别",name = "gender")
    private String gender;

    private String pwd;

    @ApiModelProperty(value = "用户电话",name = "phone")
    private String phone;

    @TableField(exist = false)
    @ApiModelProperty(value = "用户所属学校",name = "school")
    private School school;

    private String salt;

}
