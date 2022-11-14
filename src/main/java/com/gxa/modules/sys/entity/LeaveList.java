package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
//@TableName("leave_list")
public class LeaveList {

    @ApiModelProperty("id")
    @TableField("id")
    private Integer id ;

    @ApiModelProperty("学生姓名")
    @TableField("student_name")
    private String studentName;

    @ApiModelProperty("年级班级")
    @TableField("class_grade_name")
    private String classGradeName;

    @ApiModelProperty("请假时间")
    @TableField("launch_time")
    private Date launchTime;

    @ApiModelProperty("请假类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("请假理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("老师确认")
    @TableField("teacher_approve")
    private String teacherApprove;

    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty("发起人")
    @TableField("sponsor")
    private String sponsor;

    @ApiModelProperty("家长确认")
    @TableField("parents_confirm")
    private String parentsConfirm;

    @ApiModelProperty("请假状态")
    @TableField("remarks")
    private String remarks;

    @ApiModelProperty("是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;
}
