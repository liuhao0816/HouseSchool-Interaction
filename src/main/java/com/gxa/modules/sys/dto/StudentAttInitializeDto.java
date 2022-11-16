package com.gxa.modules.sys.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentAttInitializeDto {
    private Integer studentId;
    private String attStatus;
    private Date todayTime;
}
