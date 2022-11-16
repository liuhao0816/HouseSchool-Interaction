package com.gxa.modules.sys.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("V-投票管理条件查询工具类实体")
public class VoteForm {
    @ApiModelProperty(value = "当前页码",name = "page")
    private Integer page;
    @ApiModelProperty(value = "每页记录数",name = "limit")
    private Integer limit;

    @ApiModelProperty(value = "发布日期开始",name = "voteStartTime")
//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date voteStartTime;

    @ApiModelProperty(value = "发布日期截止",name = "voteEndTime")
//    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date voteEndTime;
    @ApiModelProperty(value = "状态",name = "voteStatus")
    private Integer voteStatus;
    @ApiModelProperty(value = "标题或发布者查询",name = "voteTitleOrName")
    private String voteTitleOrName;
}
