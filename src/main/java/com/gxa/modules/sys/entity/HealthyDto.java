
package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("healthy")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("健康搜索信息实体")
public class HealthyDto {
    @ApiModelProperty(name = "studentName",value = "学生姓名")
    private String studentName;
    @ApiModelProperty(name = "gradeClass",value = "年级班级")
    private String gradeClass;
    @ApiModelProperty(name = "createTime",value = "发送时间")
    private String createTime;

}
