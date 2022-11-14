package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "学生")
public class Student {
    @ApiModelProperty(value = "学籍号",name = "studentId")
    private int studentId;

    @ApiModelProperty(value = "学生名称",name = "studentName")
    private String studentName;

    @ApiModelProperty(value = "所属班级",name = "classGrade")
    private ClassGrade classGrade;

    @ApiModelProperty(value = "性别",name = "gender")
    private int gender;

    @ApiModelProperty(value = "身份证号",name = "idCard")
    private String idCard;
}
