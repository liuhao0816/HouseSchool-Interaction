package com.gxa.modules.sys.controller;

import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.service.StudentService;
import com.gxa.modules.sys.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

@Api(tags = "老师接口")
@RestController
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value="老师查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "teacherName",value ="老师姓名",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "number",value ="账号",dataType ="int"),
    })
    @GetMapping("/teacher/list")
    public Result<PageUtils> teacherList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils pageUtils = this.teacherService.queryTeacherByPage(params);
        return new Result<PageUtils>().ok(pageUtils);
    }
}
