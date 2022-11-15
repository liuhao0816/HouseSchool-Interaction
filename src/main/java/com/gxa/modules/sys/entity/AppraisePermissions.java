package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("appraise_permissions")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("评价权限实体")
public class AppraisePermissions {
    @ApiModelProperty(name = "id",value = "ID(固定为：1)")
    private Integer id;
    @ApiModelProperty(name = "headteacher",value = "班主任权限（1：为不勾选，2为勾选）")
    private Integer headteacher;
    @ApiModelProperty(name = "teacher",value = "任课老师权限（1：为不勾选，2为勾选）")
    private Integer teacher;
    @ApiModelProperty(name = "userId",value = "指定老师（传用户user_id）")
    private Integer userId;
    @ApiModelProperty(name = "otherTeacher",value = "班主任可见班级内其他老师发布的评语（1：为不勾选，2为勾选）")
    private Integer otherTeacher;
}

