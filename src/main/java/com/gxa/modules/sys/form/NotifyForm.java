package com.gxa.modules.sys.form;

import com.gxa.modules.sys.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 通知列表
 */
@Data
@ApiModel(value = "通知列表响应")
public class NotifyForm {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "范围")
    private String scope;

    @ApiModelProperty(value = "发布者")
    private String publisher;

    @ApiModelProperty(value = "发布时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "附件")
    private String enclosure;

    @ApiModelProperty(value = "已读总数")
    private Integer readCount;

    @ApiModelProperty(value = "已读人员列表")
    private List<ReadForm> readList;

    @ApiModelProperty(value = "发布状态")
    private String status;

}
