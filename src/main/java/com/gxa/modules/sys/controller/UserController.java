package com.gxa.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.gxa.common.utils.*;
import com.gxa.modules.sys.entity.User;
import com.gxa.modules.sys.entity.UserPower;
import com.gxa.modules.sys.entity.UserStatistics;
import com.gxa.modules.sys.form.UserForm;
import com.gxa.modules.sys.form.VerificationCodeForm;
import com.gxa.modules.sys.service.UserService;
import com.gxa.modules.sys.service.UserStatisticsService;
import com.gxa.modules.sys.service.UserTokenService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Api(tags = "用户接口")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @Autowired
    private UserStatisticsService userStatisticsService;

    @ApiOperation("验证码接口")
    @GetMapping("/sys/validateCode")
    public Result validateCode() throws IOException {
        Result result = new Result();

        ValidateCode validateCode = new ValidateCode();
        Map<String, Object> map = validateCode.validateCode();

        String resultValue = (String)map.get("resultValue");
        RenderedImage image =(RenderedImage) map.get("image");


        String  uuid = this.userTokenService.verificationCodeNo(resultValue);


        ByteArrayOutputStream os = new ByteArrayOutputStream();//新建流。
        ImageIO.write(image,"JPEG", os);//利用ImageIO类提供的write方法，将bi以jpeg图片的数据模式写入流。
        byte bs[] = os.toByteArray();//从流中获取数据数组。

        String encode = Base64Utils.encode(bs);

        Map<String,Object> hashMap = new HashMap<>();

        hashMap.put("image",encode);
        hashMap.put("uuid",uuid);
        result.ok(hashMap);

        return result;
    }


    @ApiOperation(value="用户登录接口")
    @PostMapping("/sys/login")
    public Result login(@RequestBody UserForm userFrom){


        String uuid = userFrom.getUuid();
        String captch = userFrom.getCaptch();

        String resultValue = userTokenService.queryByCaptch(uuid);

        if (resultValue == null){
            return new Result().error(ErrorCode.VERIFICATION_CODE_ERROR,"验证码过期");
        }

        if (!resultValue.equals(captch)){
            return new Result().error(ErrorCode.VERIFICATION_CODE_ERROR,"验证码错误");
        }


        //1、拿着用户名去 查询用户信息
        User user = this.userService.queryByUserId(userFrom.getUserId());
        if(user == null){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }

        //2、通过明文加密  对比  密文 是否一致
        String pwd = new SimpleHash("MD5", userFrom.getPassword(), user.getSalt(), 1024).toString();
        //3、不一致      返回Result.error()
        if(!pwd.equals(user.getPwd())){
            return new Result().error(ErrorCode.ACCOUNT_PASSWORD_ERROR,"用户名或密码不正确");
        }

        UserPower userPower = new UserPower();
        //4、一致     生成token 保存redis中 返回Result.ok()
        Result result = this.userTokenService.createToken(userPower);
        Map map = new HashMap();
        map.put("token",result.getData());
        return new Result().ok(map);
    }

//    @RequiresPermissions("sys:user:list")
    @ApiOperation(value="用户分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "userName",value ="查询条件",dataType ="String"),
    })
    @GetMapping("/user/list")
    public Result<PageUtils> list(@RequestParam @ApiIgnore Map<String,Object> params){
        log.info("----params-----{}----",params);
        PageUtils pageUtils = this.userService.queryByPage(params);
        return new Result<PageUtils>().ok(pageUtils);
    }


    @ApiOperation(value="用户统计")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "page",value ="当前是第几页",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "limit",value ="每页显示多少条",dataType ="int"),
            @ApiImplicitParam(paramType = "query",name = "schoolName",value ="学校名称",dataType ="String"),
    })
    @GetMapping("/userStatistics/list")
    public Result<List<UserStatistics>> userStatisticsList(@RequestParam @ApiIgnore Map<String,Object> params){
        PageInfo<UserStatistics> pageInfo = userStatisticsService.userStatisticsList(params);

        List<UserStatistics> userStatistics = pageInfo.getList();
        long total = pageInfo.getTotal();

        Result<List<UserStatistics>> r = new Result<>();
        r.ok(userStatistics,total);
        return r;
    }


}
