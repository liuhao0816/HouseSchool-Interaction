package com.gxa.modules.sys.controller;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/12
 * atime 10:33.
 */



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gxa.modules.sys.entity.dto.School;
import com.gxa.modules.sys.mapper.SchoolMapper;
import com.gxa.modules.sys.service.SchoolService;
import com.gxa.until.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;

/**
 *@author renmuqiao
 *@date 2022-11-12
 *
 */
@Api(value = "标签模块school相关的api接口")
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    //@ApiOperation("模糊查询学校名")
    //@ApiImplicitParams({
    //        @ApiImplicitParam(name="schoolname",value = "查询条件(必填)",paramType = "query",dataType = "String"),
    //})
    //@PostMapping("/selectSchool")
    //public Map selectSchool(@RequestParam(value = "schoolname",required = true)String schoolname){
    //    List<School> schools = schoolService.selectAllByName(schoolname);
    //    JsonResult jsonResult=new JsonResult();
    //    jsonResult.setCode("200");
    //    jsonResult.setMsg("查询成功");
    //    jsonResult.setData(schools);
    //    return jsonResult.getMap();
    //}
    @ApiOperation("模糊查询学校名")
    @GetMapping("/querySchool")
    public R<Page<School>>  querySchool(int page,int pageSize,String name){
        Page<School> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<School> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name!=null, School::getSchoolName,name);
        schoolService.page(pageInfo, queryWrapper);
        return R.ok(pageInfo);

    }


}
