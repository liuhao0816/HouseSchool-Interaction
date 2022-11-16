package com.gxa.modules.sys.controller;

import com.gxa.common.utils.Result;
import com.gxa.modules.sys.dto.AttendanceDateDto;
import com.gxa.modules.sys.service.AttendanceDateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "点名")
@RestController
public class AttendanceDateController {
    @Autowired
    private AttendanceDateService attendanceDateService;

    @PostMapping("/AttendanceDateDto")
    @ApiOperation("查询所有记录")
    public Result<List<AttendanceDateDto>> queryByGradeName(@RequestBody String class_grade_name) {
        List<AttendanceDateDto> allLeaveListDtos = attendanceDateService.queryByGradeName(class_grade_name);
        Integer size = allLeaveListDtos.size();
        long total = size.longValue();
        Result<List<AttendanceDateDto>> result = new Result().ok(allLeaveListDtos,total);
        return result;
    }
}
