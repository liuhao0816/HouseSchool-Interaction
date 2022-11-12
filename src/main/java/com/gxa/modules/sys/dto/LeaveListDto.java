package com.gxa.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("请假多条件查询")
public class LeaveListDto {

    @ApiModelProperty("start_time")
    @TableField("开始时间")
    private Date startTime;

    @ApiModelProperty("end_time")
    @TableField("结束时间")
    private Date endTime;

    @ApiModelProperty("type")
    @TableField("请假类型")
    private String type;

    @ApiModelProperty("remarks")
    @TableField("请假状态")
    private String remarks;

    @ApiModelProperty("class_grade")
    @TableField("年假班级")
    private String classGrade;
}
