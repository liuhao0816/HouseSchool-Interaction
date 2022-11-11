package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class ClassTable {
    @ApiModelProperty("class_id")
    @TableField("班级id")
    private Integer classId ;

    @ApiModelProperty("class_grade")
    @TableField("班级年纪")
    private String classGrade;

    @ApiModelProperty("class_school_id")
    @TableField("学校id")
    private Integer classSchoolId;

    @ApiModelProperty("remarks")
    @TableField("删除备注")
    private String remarks;


}
