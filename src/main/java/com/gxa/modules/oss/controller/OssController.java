package com.gxa.modules.oss.controller;

import com.gxa.common.exception.ResultException;
import com.gxa.common.utils.Result;
import com.gxa.modules.oss.factory.OSSFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss")
@Slf4j
@Api(tags = "文件图片上传接口")
public class OssController {

    /**
     * 上传文件
     */
    @ApiOperation("文件上传")
    @PostMapping("/file/upload")
//    @RequiresPermissions("sys:oss:all")
    public Result uploadFile(@RequestParam("file") @ApiParam(value = "文件",name = "file",required = true)MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ResultException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(),"文件," + suffix);

        Map map = new HashMap();
        map.put("url",url);
        return new Result().ok(map);
    }

    @ApiOperation("图片上传")
    @PostMapping("/img/upload")
//    @RequiresPermissions("sys:oss:all")
    public Result uploadImg(@RequestParam("img") @ApiParam(value = "图片",name = "img",required = true)MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new ResultException("上传图片不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(),"img," + suffix);

        Map map = new HashMap();
        map.put("url",url);
        return new Result().ok(map);
    }
}