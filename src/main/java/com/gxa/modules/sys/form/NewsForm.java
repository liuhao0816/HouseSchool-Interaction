package com.gxa.modules.sys.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 七天
 * @version 1.1
 * @date 2022/11/16
 */
@Data
@ApiModel(value = "消息发布列表")
public class NewsForm {

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "发布数量")
    private Integer count;
    @ApiModelProperty(value = "占比")
    private String proportion;
}
