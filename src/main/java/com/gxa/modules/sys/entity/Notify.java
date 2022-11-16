package com.gxa.modules.sys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;



/**
 * 通知列表
 */
@Data
@TableName("notify_content")
public class Notify implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private NotifyType notifyType;
    private Content content;
    private Scope scope;
    private String remark;
    @TableLogic
    private Integer isDelete;

}
