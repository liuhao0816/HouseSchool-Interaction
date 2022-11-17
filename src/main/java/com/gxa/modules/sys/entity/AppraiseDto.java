
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
@ApiModel("评价搜索信息实体")
public class AppraiseDto {
    @ApiModelProperty(name = "studentName",value = "学生姓名")
    private String studentName;
    @ApiModelProperty(name = "gradeClass",value = "年级班级")
    private String gradeClass;
    @ApiModelProperty(name = "appraiseTime",value = "发布时间")
    private String appraiseTime;
}
