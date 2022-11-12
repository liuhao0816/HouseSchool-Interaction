package com.gxa.entity;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/11
 * atime 16:33.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class School implements Serializable {

    @ApiModelProperty(hidden = true)
    private int schoolId;//学校id

    @ApiModelProperty(name = "school_name",value = "学校名称", required = true )
    private String schoolName;//学校名称

    private String schoolAddress;//学校地址

    private String remarks;//备注


}
