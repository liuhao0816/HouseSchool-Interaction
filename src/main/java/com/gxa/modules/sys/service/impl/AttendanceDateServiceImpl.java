package com.gxa.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxa.modules.sys.dto.AttendanceDateDto;
import com.gxa.modules.sys.entity.AttendanceDate;
import com.gxa.modules.sys.mapper.AttendanceDateMapper;
import com.gxa.modules.sys.service.AttendanceDateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceDateServiceImpl extends ServiceImpl<AttendanceDateMapper, AttendanceDate> implements AttendanceDateService {


    @Override
    public List<AttendanceDateDto> queryByGradeName(String class_grade_name) {
        List<AttendanceDateDto> attendanceDateDtos=this.baseMapper.queryByGradeName(class_grade_name);
        return attendanceDateDtos;
    }
}
