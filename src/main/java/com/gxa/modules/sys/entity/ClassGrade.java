package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("class_grade")
public class ClassGrade {
    @ApiModelProperty("class_id")
    @TableField("班级id")
    private Integer classId ;

    @ApiModelProperty("class_grade_name")
    @TableField("班级年纪")
    private String classGradeName;

    @ApiModelProperty("class_school_id")
    @TableField("学校id")
    private Integer classSchoolId;

    @ApiModelProperty("class_teacher")
    @TableField("班主任")
    private Integer classTeacher;

    @ApiModelProperty("remarks")
    @TableField("删除备注")
    private String remarks;


}
