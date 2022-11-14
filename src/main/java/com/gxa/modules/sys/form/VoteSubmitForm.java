package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("V-进行中投票活动提交实体")
public class VoteSubmitForm {
    @ApiModelProperty(value = "当前投票活动ID",name = "voteId")
    private Integer voteId;
    @ApiModelProperty(value = "当前登录用户ID",name = "userId")
    private Integer userId;
    @ApiModelProperty(value = "投票选项选中ID",name = "voteOptionIds")
    private List<Integer> voteOptionIds;
}
