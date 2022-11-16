package com.gxa.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

import java.util.Date;

/**通知内容
 */
@Data
public class Content {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String enclosure;
    private String picture;
    private Scope scope;
    private Date releaseTime;

}
