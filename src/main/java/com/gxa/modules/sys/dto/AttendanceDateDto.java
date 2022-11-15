package com.gxa.modules.sys.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AttendanceDateDto {
    @ApiModelProperty("id")
    private Integer id ;

    @ApiModelProperty("学生姓名")
    private String studentName;

    @ApiModelProperty("年级班级")
    private String classGradeName;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("今日考勤状态")
    private String attendanceStatus;

    @ApiModelProperty("电话号码")
    private String phone;

    @ApiModelProperty("考勤时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date attendanceDate;
}
