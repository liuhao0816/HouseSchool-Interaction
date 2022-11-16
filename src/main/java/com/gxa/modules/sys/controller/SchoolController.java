package com.gxa.modules.sys.controller;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 10:33.
 */



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.common.utils.JsonResult;
import com.gxa.modules.sys.entity.School;
import com.gxa.modules.sys.service.SchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
@Api(tags = "学校接口")
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;


    @ApiOperation("模糊查询学校名")
    @GetMapping("/querySchool")
    public R<Page<School>>  querySchool(int page, int pageSize, String name){
        Page<School> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<School> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null, School::getSchoolName,name);
        schoolService.page(pageInfo, queryWrapper);
        return R.ok(pageInfo);

    }
    @ApiOperation("查询学校代码")
    @GetMapping("/select/{id}")
    public R<Page<School>> selectById(@PathVariable Integer id){
        Page<School> pageInfo = new Page<>();
        LambdaQueryWrapper<School> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(id!=null, School::getSchoolId,id);
        schoolService.page(pageInfo, queryWrapper);
        return R.ok(pageInfo);

    }
    @PostMapping("/searchAddressByType")
    @ApiOperation("根据地区类型查找")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "地区",paramType = "query"),})
    public Map searchAddressByType(@NotEmpty(message = "地区不能为空") @RequestParam("name")String name){
        List<School> schools = schoolService.searchAddressByType(name);
        JsonResult jsonResult = new JsonResult();
        jsonResult.setCode("200");
        jsonResult.setMsg("查询成功");
        jsonResult.setData(schools);
        return jsonResult.getMap();
    }



}
