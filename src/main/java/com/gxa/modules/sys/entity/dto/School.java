package com.gxa.modules.sys.entity.dto;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/11
 * atime 16:33.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 *@author renmuqiao
 *@date 2022-11-11
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "学校")
public class School implements Serializable {

    @ApiModelProperty(value = "学校编号",name = "schoolId")
    private int schoolId;

    @ApiModelProperty(value = "学校名称",name = "schoolName")
    private String schoolName;

    @ApiModelProperty(value = "学校地址",name = "schoolAddress")
    private String schoolAddress;

    @ApiModelProperty(value = "备注",name = "remarks")
    private String remarks;

}
