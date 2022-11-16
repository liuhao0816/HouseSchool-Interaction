package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class AttendanceDate {
    @ApiModelProperty("id")
    @TableField("id")
    private Integer id ;


    @ApiModelProperty("学生id")
    @TableField("student_id")
    private String studentId;

    @ApiModelProperty("今日考勤状态")
    @TableField("attendance_status")
    private String attendanceStatus;


    @ApiModelProperty("请假时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    @TableField("attendance_date")
    private Date attendanceDate;
}
