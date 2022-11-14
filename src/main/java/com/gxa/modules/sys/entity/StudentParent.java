package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;


@Data
@ApiModel(value = "家长")
public class StudentParent {

    @ApiModelProperty(value = "关系学生",name = "students")
    private Set<Student> students;

    @ApiModelProperty(value = "家长用户信息",name = "user")
    private User user;

    @ApiModelProperty(value = "关系",name = "relation")
    private String relation;


}
