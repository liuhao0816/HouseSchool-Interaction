package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
通知类型
 */

@Data
@TableName("notify_type")
public class NotifyType implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**通知类型*/
    private String type;
    /**属性*/
    private String attribute;
    /**状态*/
    private String status;
    /**备注*/
    private String remark;
    /**逻辑删除*/
    @TableLogic
    private Integer isDelete;
}
