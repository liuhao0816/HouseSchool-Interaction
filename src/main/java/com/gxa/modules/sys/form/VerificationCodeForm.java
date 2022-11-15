package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.image.RenderedImage;
import java.io.Serializable;

@Data
@ApiModel(value = "验证码")
public class VerificationCodeForm implements Serializable {
    @ApiModelProperty(value = "验证码编号",name = "uuid")
    private String uuid;
    @ApiModelProperty(value = "验证码图片",name = "image")
    private RenderedImage image;
}
