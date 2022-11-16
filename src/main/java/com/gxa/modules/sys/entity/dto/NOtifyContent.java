package com.gxa.modules.sys.entity.dto;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 9:04.
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
@Data
@TableName("notify_content")
public class NOtifyContent {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;
    /**标题*/
    private String content;
    /**内容*/
    private String writer;
    /**署名*/
    private String enclosure;
    /**附件*/
    private String picture;
    /**图片*/
    private Integer scope_id;
    /**范围Id*/
    private String release_time;
    /**发布时间*/
    @TableLogic
    private Integer isDelete;
    /**逻辑删除*/

}
