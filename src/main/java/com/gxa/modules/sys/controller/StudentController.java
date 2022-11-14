package com.gxa.modules.sys.controller;

import com.gxa.common.utils.ErrorCode;
import com.gxa.common.utils.PageUtils;
import com.gxa.common.utils.Result;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserPower;
import com.gxa.modules.sys.form.UserForm;
import com.gxa.modules.sys.service.StudentService;
import com.gxa.modules.sys.service.UserService;
import com.gxa.modules.sys.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "学生接口")
@RestController
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation(value="学生查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "studentName",value ="学生姓名",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "number",value ="学籍号或证件号",dataType ="String"),
    })
    @GetMapping("/student/list")
    public Result<PageUtils> studentList(@RequestParam @ApiIgnore Map<String,Object> params){

        PageUtils pageUtils = this.studentService.queryStutentByPage(params);
        return new Result<PageUtils>().ok(pageUtils);
    }
}
