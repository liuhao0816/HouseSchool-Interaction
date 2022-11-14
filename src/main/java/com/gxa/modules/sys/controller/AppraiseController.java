package com.gxa.modules.sys.controller;

import com.gxa.common.utils.PageUtils;
import com.gxa.modules.sys.entity.*;
import com.gxa.modules.sys.service.AppPermissionsService;
import com.gxa.modules.sys.service.AppraiseService;
import com.gxa.modules.sys.service.HealthyService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gxa.common.utils.Result;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "评价接口")
@RestController
@Slf4j
public class AppraiseController {
    @Autowired
    private AppraiseService appraiseService;
    @Autowired
    private AppPermissionsService appPermissionsService;
    @Autowired
    private HealthyService healthyService;


    @GetMapping("/appraise01")
    @ResponseBody
    @ApiOperation(value = "查找接口",notes = "查找全部评价",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok",response = Appraise.class)
    })
    public Result appraiseList(){
        List<Appraise> appraises = this.appraiseService.queryAll();
        Map map = new HashMap();
        map.put("appraises",appraises);
        return new Result<>().ok(map);
    }

    @PostMapping("/appraise/add")
    @ResponseBody
    @ApiOperation(value = "添加",notes = "添加评价",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok",response = Appraise.class)
    })
    public Result appraiseAdd(@RequestBody Appraise appraise){
        try {
            this.appraiseService.add(appraise);
        } catch (Exception e) {
            e.printStackTrace();
            new Result<>().ok("fail");
        }
        return new Result<>().ok("succes");
    }


    @GetMapping("/appraise02")
    @ResponseBody
    @ApiOperation(value = "查找接口",notes = "评价修改前的遍历查询",httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok",response = Appraise.class)
    })
    public Result appraiseUpdateList(@RequestParam String publisher,String appraiseTime){
        List<Appraise> appraises = this.appraiseService.queryByPublisher(publisher,appraiseTime);
        Map map = new HashMap();
        map.put("appraises",appraises);
        return new Result<>().ok(map);
    }

    @PutMapping("/appraise/update")
    @ResponseBody
    @ApiOperation(value = "修改",notes = "修改评价",httpMethod = "PUT")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok",response = Appraise.class)
    })
    public Result appraiseUpdate(@RequestBody Appraise appraise){
        try {
            this.appraiseService.update(appraise);
        } catch (Exception e) {
            e.printStackTrace();
            new Result<>().ok("fail");
        }
        return new Result<>().ok("succes");
    }

    @DeleteMapping("/appraise/delete")
    @ApiOperation(value = "删除接口",notes = "评价删除",httpMethod = "DELETE")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok")
    })
    public Result appraiseDelete(@RequestParam String publisher,String appraiseTime){
        try {
            System.out.println(publisher);
            System.out.println(appraiseTime);
            this.appraiseService.delete(publisher,appraiseTime);
        } catch (Exception e) {
            e.printStackTrace();
            new Result<>().ok("fail");
        }
        return new  Result<>().ok("succes");
    }

    @PostMapping("/appraise03")
    @ResponseBody
    @ApiOperation(value = "查询接口",notes = "搜索查询",httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = 0,message = "ok",response = Appraise.class)
    })
    public Result appraiseSearch(@RequestBody AppraiseDto appraiseDto) {
        String tollDate = appraiseDto.getAppraiseTime();
        System.out.println(tollDate);
        String firstDateTime = null;
        String lastDateTime = null;

        if (tollDate != null && !"".equals(tollDate)) {
            String[] dateTime = tollDate.split(",");
            firstDateTime = dateTime[0].trim();
            lastDateTime = dateTime[1].trim();
            try {
                List<Appraise> appraises = this.appraiseService.queryByAppraiseDto(firstDateTime, lastDateTime, appraiseDto);
                Map map = new HashMap();
                map.put("appraises", appraises);
                return new Result<>().ok(map);
            } catch (Exception e) {
                e.printStackTrace();
                new Result<>().ok("fail");
            }
        } else {
            List<Appraise> appraises = this.appraiseService.queryByAppraiseDtos(appraiseDto);
            Map map = new HashMap();
            map.put("appraises", appraises);
            return new Result<>().ok(map);
        }
        return new Result<>().ok();
    }
    @ApiOperation(value="评价分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "username",value ="查询条件",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "order",value ="升序asc，降序填desc",dataType ="String"),
            @ApiImplicitParam(paramType = "query",name = "sidx",value ="排序字段",dataType ="String"),

    })
    @GetMapping("/appraise/list01")
    public Result<PageUtils> list01(@RequestParam @ApiIgnore Map<String,Object> params){
        log.info("----params-----{}----",params);
        PageUtils pageUtils = this.appraiseService.queryByPage01(params);
        return new Result<PageUtils>().ok(pageUtils);
    }


    @GetMapping("/appraise/PermissionsTeacher")
    @ResponseBody
    @ApiOperation(value = "查找接口",notes = "权限下拉查询全部老师",httpMethod = "GET")
    public Result appraisePermissionsTeacher(){
        List<User> teacher = this.appraiseService.queryByPermissionsTeacher();

        return new  Result<>().ok(teacher);
    }
    @PutMapping("/appraise/PermissionsLaunch")
    @ResponseBody
    @ApiOperation(value = "修改接口",notes = "评价权限",httpMethod = "PUT")
    public Result appraisePermissionsLaunch(@RequestParam AppraisePermissions appraisePermissions){
        try {
            this.appPermissionsService.queryByPermissionsLaunch(appraisePermissions);
        }catch (Exception e){
            e.printStackTrace();
            new Result<>().ok("fail");
        }
        return new  Result<>().ok("succes");
    }

    @GetMapping("/appraise/recipient01")
    @ResponseBody
    @ApiOperation(value = "查找接口",notes = "添加处查询用户孩子班级",httpMethod = "GET")
    public Result healthyRecipient01(@RequestParam int userId){

        AppraisePermissions appraisePermissions = this.appPermissionsService.queryByTeacherType();
        int teacherType = appraisePermissions.getTeacher();
        int appraiseUserId = appraisePermissions.getUserId();
        int headteacherType = appraisePermissions.getHeadteacher();
        System.out.println(userId);
        List<ClassGrade> healthyClass = this.healthyService.queryByHealthyClass(userId);
        Map map = new HashMap();
        map.put("healthyClass", healthyClass);
        if (teacherType == 2 || userId == appraiseUserId){
            //通过老师查询
            List<ClassGrade> headteacher = this.appPermissionsService.queryByPermissionsHeadteacher(userId);
            map.put("headteacher", headteacher);
            return new Result<>().ok(map);
        }else if (headteacherType == 2 && teacherType == 1){
            //通过班主任查询
            List<ClassGrade> teacher = this.appPermissionsService.queryByPermissionsTeacher(userId);
            map.put("teacher", teacher);
            return new Result<>().ok(map);
        }else {
            return new  Result<>().ok();
        }

    }
}
