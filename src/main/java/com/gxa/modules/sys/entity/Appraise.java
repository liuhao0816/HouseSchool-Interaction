package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("appraise")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("评价信息实体")
public class Appraise {
    @ApiModelProperty(name = "id",value = "序号")
    private Integer id;
    @ApiModelProperty(name = "studentName",value = "学生姓名")
    private String studentName;
    @ApiModelProperty(name = "gradeClass",value = "年级班级")
    private String gradeClass;
    @ApiModelProperty(name = "type",value = "评价类型")
    private String type;
    @ApiModelProperty(name = "content",value = "评价类容")
    private String content;
    @ApiModelProperty(name = "score",value = "分值")
    private String score;
    @ApiModelProperty(name = "image",value = "图片地址")
    private String image;
    @ApiModelProperty(name = "publisher",value = "发布者")
    private String publisher;
    @ApiModelProperty(name = "appraiseTime",value = "发布时间")
    private String appraiseTime;

}
