package com.gxa.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxa.modules.sys.dto.AttendanceDateDto;
import com.gxa.modules.sys.entity.AttendanceDate;

import java.util.List;

public interface AttendanceDateMapper extends BaseMapper<AttendanceDate> {
    List<AttendanceDateDto> queryByGradeName(String class_grade_name);
}
