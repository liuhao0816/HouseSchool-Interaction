package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Student {

    @ApiModelProperty("id")
    @TableField("id")
    private Integer id ;

    @ApiModelProperty("student_name")
    @TableField("学生姓名")
    private String studentName;

    @ApiModelProperty("student_id")
    @TableField("学籍号")
    private Integer studentId ;

    @ApiModelProperty("class_id")
    @TableField("班级")
    private String classId;

    @ApiModelProperty("gender")
    @TableField("性别")
    private String gender;

    @ApiModelProperty("Id_card")
    @TableField("身份证号")
    private String IdCard;

    @ApiModelProperty("school_study")
    @TableField("所属学校")
    private String schoolStudy;

    @ApiModelProperty("is_deleted")
    @TableField("用户状态")
    private Integer isDeleted ;
}
