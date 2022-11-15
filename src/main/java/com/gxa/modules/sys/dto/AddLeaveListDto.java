package com.gxa.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("添加")
public class AddLeaveListDto {
    @ApiModelProperty("id")
    @TableField("id")
    private Integer id ;

    @ApiModelProperty("学生id")
    @TableField("student_id")
    private String studentId;


    @ApiModelProperty("请假时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @TableField("launch_time")
    private String launchTime;

    @ApiModelProperty("请假类型")
    @TableField("leave_type")
    private String leaveType;

    @ApiModelProperty("请假理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("老师确认")
    @TableField("teacher_approve")
    private String teacherApprove;

    @ApiModelProperty("开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @TableField("start_time")
    private String startTime;

    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @TableField("end_time")
    private String endTime;

    @ApiModelProperty("发起人")
    @TableField("sponsor")
    private String sponsor;

    @ApiModelProperty("家长确认")
    @TableField("parents_confirm")
    private String parentsConfirm;

    @ApiModelProperty("请假状态")
    @TableField("remarks")
    private String remarks;

    @TableLogic
    private Integer isDeleted;
}
