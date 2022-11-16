package com.gxa.modules.sys.service;/**
 * @author ${RenMuQiao}.
 * adate 2022/11/15
 * atime 14:19.
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.gxa.modules.sys.entity.dto.AppraiseStat;

import java.util.List;

/**
 *@author renmuqiao
 *@date 2022-11-15
 *
 */
public interface AppraiseStatService extends IService<AppraiseStat> {
    //查询所有
    List selectAll();
}
