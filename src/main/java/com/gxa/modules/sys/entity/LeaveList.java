package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("leave_list")
public class LeaveList {

    @ApiModelProperty("id")
    @TableField("id")
    private Integer id ;

    @ApiModelProperty("student_id")
    @TableField("学生序号")
    private Integer studentId;

    @ApiModelProperty("launch_time")
    @TableField("请假时间")
    private Date lanuchTime;

    @ApiModelProperty("type")
    @TableField("请假类型")
    private String type;

    @ApiModelProperty("reason")
    @TableField("请假理由")
    private String reason;

    @ApiModelProperty("teacher_approve")
    @TableField("老师确认")
    private String teacherApprove;

    @ApiModelProperty("start_time")
    @TableField("开始时间")
    private Date startTime;

    @ApiModelProperty("end_time")
    @TableField("结束时间")
    private Date endTime;

    @ApiModelProperty("sponsor")
    @TableField("发起人")
    private String sponsor;

    @ApiModelProperty("parents_confirm")
    @TableField("家长确认")
    private String parentsConfirm;

    @ApiModelProperty("remarks")
    @TableField("删除备注")
    private String remarks;


}
