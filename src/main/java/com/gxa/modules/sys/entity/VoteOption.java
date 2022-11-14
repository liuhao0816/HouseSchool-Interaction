package com.gxa.modules.sys.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("V-投票选项实体")
public class VoteOption {
    @ApiModelProperty(value = "投票选项ID",name = "id")
    private int id;
    @ApiModelProperty(value = "投票选项内容",name = "voteOption")
    private String voteOption;
    @ApiModelProperty(value = "投票选项票数",name = "voteOptionPoll")
    private Integer voteOptionPoll;
}
