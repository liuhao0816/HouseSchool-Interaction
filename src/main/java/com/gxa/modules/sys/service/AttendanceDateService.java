package com.gxa.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.dto.AttendanceDateDto;
import com.gxa.modules.sys.entity.AttendanceDate;

import java.util.List;

public interface AttendanceDateService extends IService<AttendanceDate> {
    List<AttendanceDateDto> queryByGradeName(String class_grade_name);
}
